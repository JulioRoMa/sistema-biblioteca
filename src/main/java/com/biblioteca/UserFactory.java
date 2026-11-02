package com.biblioteca;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserFactory {
    private UserFactory() {
    }

    public static User fromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                String.valueOf(resultSet.getInt("id_usuario")),
                resultSet.getString("nombre"),
                resultSet.getString("apellidos"),
                resultSet.getString("correo"),
                resultSet.getString("telefono"),
                resultSet.getString("direccion"),
                resultSet.getDate("fecha_registro").toString(),
                resultSet.getString("estado")
        );
    }
}
