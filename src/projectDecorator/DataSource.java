package projectDecorator;

//Interface DataSource para operações de manipulação de dados.
//Define métodos para gravar (writeData) e ler (readData) dados.
public interface DataSource {
 
 void writeData(String data); // Armazena os dados fornecidos.

 String readData(); // Retorna os dados lidos.
}