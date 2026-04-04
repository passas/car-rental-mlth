package org.acme.app.model.user.mapper;

import org.acme.api.rest.dto.UserDTO;
import org.acme.app.model.user.UserModel;
import org.acme.db.psql.entity.user.UserEntity;

public class UserModelMapper
{
    public static UserModel fromDTO(UserDTO dto)
    {
        UserModel model = new UserModel();
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        return model;
    }

    public static UserModel fromEntity(UserEntity entity)
    {
        UserModel model = new UserModel();
        model.setUsername(entity.getUsername());
        model.setPassword(entity.getPassword());
        return model;
    }
}
