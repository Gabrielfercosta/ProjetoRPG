package ProjetoRpgBattleEngine;

import java.sql.*;

public class PersonagemDAO{
    public void salvar(Personagem p) {
        String sql = "INSERT INTO personagens (nome, vida_atual, vida_maxima, poder, mana_atual, mana_maxima) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getNome());
            pstmt.setInt(2, p.getVidaAtual());
            pstmt.setInt(3, p.getVidaMaxima());
            pstmt.setInt(4, p.getPoder());
            pstmt.setInt(5, p.getManaAtual());
            pstmt.setInt(6, p.getManaMaxima());

            pstmt.executeUpdate();
            System.out.println("Personagem " + p.getNome() + " salvo");

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
