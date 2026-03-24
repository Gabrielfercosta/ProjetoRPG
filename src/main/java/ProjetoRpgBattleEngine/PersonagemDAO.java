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

    public Personagem buscarPorNome(String nomeBusca) {
        String sql = "SELECT * FROM personagens WHERE nome = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomeBusca);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Personagem(
                        rs.getString("nome"),
                        rs.getInt("vida_atual"),
                        rs.getInt("vida_maxima"),
                        rs.getInt("poder"),
                        rs.getInt("mana_atual"),
                        rs.getInt("mana_maxima")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
        return null;
    }

    public void salvarProgresso(Personagem p) {
        String sql = "UPDATE personagens SET vida_atual = ?, mana_atual = ? WHERE nome = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, p.getVidaAtual());
            pstmt.setInt(2, p.getManaAtual());
            pstmt.setString(3, p.getNome());

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
            System.out.println("Personagem " + nome + " apagado");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
