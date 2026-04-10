package org.acme.app.service.payment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.app.model.payment.PaymentModel;
import org.acme.app.model.payment.mapper.PaymentModelMapper;
import org.acme.db.psql.entity.payment.PaymentEntity;
import org.acme.db.psql.entity.payment.mapper.PaymentEntityMapper;
import org.acme.db.psql.repository.payment.PaymentRepository;
import org.acme.db.psql.repository.payment.exception.PaymentRepositoryConstraintRentalForeignKeyException;
import org.acme.db.psql.repository.payment.exception.PaymentRepositoryConstraintRentalUniqueException;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PaymentService
{
    private Logger log;
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository)
    {
        this.log = Logger.getLogger(PaymentService.class.getSimpleName());
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentModel createPayment(PaymentModel paymentModel) throws PaymentRepositoryConstraintRentalForeignKeyException, PaymentRepositoryConstraintRentalUniqueException
    {
        PaymentEntity paymentEntity = PaymentEntityMapper.fromModel(paymentModel);
        this.log.infof("Creating a payment due: %s", paymentEntity);
        try
        {
            this.paymentRepository.persist(paymentEntity);
            this.paymentRepository.flush();
        }
        catch (ConstraintViolationException e)
        {
            String constraintName = e.getConstraintName();
            if ("fk_payment_rental_rental_id".equals(constraintName))
            {
                this.log.error("Rental does not exists");
                throw new PaymentRepositoryConstraintRentalForeignKeyException();
            }
            else if("uq_payment_rental_id".equals(constraintName))
            {
                this.log.error("The rental has already an associated payment");
                throw new PaymentRepositoryConstraintRentalUniqueException();
            }
        }
        this.log.infof("Payment due created: %s", paymentEntity);
        return PaymentModelMapper.fromEntity(paymentEntity);
    }

    @Transactional
    public boolean isPaymentDueOpened(PaymentModel model)
    {
        return this.paymentRepository.isOpened(PaymentEntityMapper.fromModel(model));
    }

    @Transactional
    public boolean isPaymentDueClosed(PaymentModel model)
    {
        PaymentEntity entity = PaymentEntityMapper.fromModel(model);
        return this.paymentRepository.isOpened(entity) && this.paymentRepository.isPayed(entity);
    }

    @Transactional
    public PaymentModel payPayment(PaymentModel paymentModel)
    {
        PaymentEntity paymentEntity = this.paymentRepository.findById(paymentModel.getId());
        paymentEntity.setPayedAt(paymentModel.getPayedAt());
        this.log.infof("Ending a payment due:\n %s", paymentEntity);
        try
        {
            this.paymentRepository.flush();
        }
        catch (ConstraintViolationException e)
        {
            String constraintName = e.getConstraintName();
            if ("_".equals(constraintName))
                this.log.error(e);
        }
        this.log.infof("Payment due ended:\n %s", paymentEntity);
        return PaymentModelMapper.fromEntity(paymentEntity);
    }
}
