package com.opet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opet.util.Reader;
public class Main
{


    public static void main(String[] args) throws Exception
    {
        // TODO Auto-generated method stub
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                        "system","system");
        int opcao = 0;
        while(opcao != 9 ) {
            System.out.println("Escolha uma opção");
            System.out.println("1 - Inserir aluno");
            System.out.println("2 - Listar Aluno");
            System.out.println("9 - Sair");
            opcao = Reader.readInt();

            switch (opcao)
            {
                case 1:
                    System.out.println("Digite o nome do aluno:");
                    String nomeAluno = Reader.readString();

                    PreparedStatement stmt =
                                    conn.prepareStatement("insert into tiago_aluno (codigo, nome) values ("
                                                    + "(select coalesce(max(codigo)+1,1) from tiago_aluno), ?)");
                    //stmt.setInt(1,1);
                    stmt.setString(1,nomeAluno);
                    try {
                        ResultSet rs = stmt.executeQuery();
                        System.out.println("Registro gravado com sucesso.");

                    } catch(Exception e) {
                        System.out.println("Erro ao gravar registro.");
                    }

                    break;
                case 2:
                    PreparedStatement stmtBusca =
                    conn.prepareStatement("select  * from tiago_aluno");
                    ResultSet rs = stmtBusca.executeQuery();
                    while(rs.next()){
                        System.out.print(rs.getInt("codigo"));
                        System.out.println("-"+rs.getString("nome"));
                        }
                    break;

                default:
                    break;
            }

        }


    }

}
