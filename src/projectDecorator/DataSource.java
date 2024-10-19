package projectDecorator;

//Interface DataSource para opera��es de manipula��o de dados.
//Define m�todos para gravar (writeData) e ler (readData) dados.
public interface DataSource {
 
 void writeData(String data); // Armazena os dados fornecidos.

 String readData(); // Retorna os dados lidos.
}