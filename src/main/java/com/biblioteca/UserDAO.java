package com.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String BASE_QUERY = """
            SELECT id_usuario, nombre, apellidos, correo, telefono, direccion,
                   fecha_registro, estado
            FROM usuarios
            """;

    public List<User> find(String searchText, String status) throws SQLException {
        String text = searchText == null ? "" : searchText.trim();
        String selectedStatus = status == null ? "" : status.trim();
        boolean filterText = !text.isEmpty();
        boolean filterStatus = !selectedStatus.isEmpty() && !"Todos".equalsIgnoreCase(selectedStatus);

        StringBuilder sql = new StringBuilder(BASE_QUERY).append(" WHERE 1 = 1");
        if (filterText) {
            sql.append(" AND LOWER(CONCAT(nombre, ' ', apellidos)) LIKE LOWER(?)");
        }
        if (filterStatus) {
            sql.append(" AND estado = ?");
        }
        sql.append(" ORDER BY id_usuario");

        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int parameterIndex = 1;
            if (filterText) {
                statement.setString(parameterIndex++, "%" + text + "%");
            }
            if (filterStatus) {
                statement.setString(parameterIndex, selectedStatus);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(UserFactory.fromResultSet(resultSet));
                }
            }
        }
        return users;
    }

    public List<String> findStatuses() throws SQLException {
        List<String> statuses = new ArrayList<>();
        String sql = "SELECT DISTINCT estado FROM usuarios WHERE estado IS NOT NULL AND estado <> '' ORDER BY estado";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                statuses.add(resultSet.getString("estado"));
            }
        }
        return statuses;
    }
}
