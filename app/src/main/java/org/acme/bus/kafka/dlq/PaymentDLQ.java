package org.acme.bus.kafka.dlq;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.bus.kafka.event.PaymentEvent;
import org.acme.db.psql.entity.payment.PaymentEntity;
import org.acme.db.psql.entity.paymentdlq.PaymentDLQEntity;
import org.acme.db.psql.repository.payment.PaymentRepository;
import org.acme.db.psql.repository.paymentdlq.PaymentDLQRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PaymentDLQ
{
    private Logger log;
    private PaymentRepository paymentRepository;
    private PaymentDLQRepository paymentDLQRepository;

    public PaymentDLQ(PaymentRepository paymentRepository, PaymentDLQRepository paymentDLQRepository)
    {
        this.log = Logger.getLogger(PaymentDLQ.class.getSimpleName());
        this.paymentRepository = paymentRepository;
        this.paymentDLQRepository = paymentDLQRepository;
    }

    @Transactional
    public void enqueue(PaymentEvent paymentEvent)
    {
        this.log.infof("Enqueuing payment event...\n %s", paymentEvent);
        PaymentEntity paymentEntity = this.paymentRepository.findById(paymentEvent.getPaymentId());
        PaymentDLQEntity paymentDLQEntity = new PaymentDLQEntity();
        paymentDLQEntity.setPaymentEntity(paymentEntity);
        try
        {
            this.paymentDLQRepository.persist(paymentDLQEntity);
            this.paymentDLQRepository.flush();
        }
        catch (ConstraintViolationException e)
        {
            this.log.error("DLQ persist failed", e);
        }
        catch (Exception e)
        {
            this.log.error("DLQ persist failed", e);
        }
        this.log.infof("Payment event enqueued:\n %s", paymentEntity);
    }
}
