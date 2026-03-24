package ProjetoRpgBattleEngine;

import java.sql.*;

public class PersonagemDAO{
    public void salvar(Jogador j) {
        String sql = "INSERT INTO personagens (nome, vida_atual, vida_maxima, poder, mana_atual, mana_maxima, level, xp_atual) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, j.getNome());
            pstmt.setInt(2, j.getVidaAtual());
            pstmt.setInt(3, j.getVidaMaxima());
            pstmt.setInt(4, j.getPoder());
            pstmt.setInt(5, j.getManaAtual());
            pstmt.setInt(6, j.getManaMaxima());
            pstmt.setInt(7, j.getLevel());
            pstmt.setInt(8, j.getXpAtual());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Jogador buscarPorNome(String nomeBusca) {
        String sql = "SELECT * FROM personagens WHERE nome = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomeBusca);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Jogador(
                        rs.getString("nome"),
                        rs.getInt("vida_atual"),
                        rs.getInt("vida_maxima"),
                        rs.getInt("poder"),
                        rs.getInt("mana_atual"),
                        rs.getInt("mana_maxima"),
                        rs.getInt("level"),
                        rs.getInt("xp_atual")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
        return null;
    }

    public void salvarProgresso(Jogador j) {
        String sql = "UPDATE personagens SET vida_atual = ?, mana_atual = ?, level = ?, xp_atual = ? WHERE nome = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, j.getVidaAtual());
            pstmt.setInt(2, j.getManaAtual());
            pstmt.setInt(3, j.getLevel());
            pstmt.setInt(4, j.getXpAtual());
            pstmt.setString(5, j.getNome());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar progresso: " + e.getMessage());
        }
    }

    public void deletarPersonagem(String nome) {
        String sql = "DELETE FROM personagens WHERE nome = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
