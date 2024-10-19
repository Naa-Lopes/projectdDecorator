package projectDecorator;

//Classe abstrata que implementa a interface DataSource, servindo como um decorador base.
//Permite adicionar comportamentos a fontes de dados existentes.
public abstract class DataSourceDecorator implements DataSource {
 
 // Referência ao objeto DataSource decorado.
 private DataSource wrappee;

 // Construtor que recebe e armazena a fonte de dados a ser decorada.
 DataSourceDecorator(DataSource source) {
     this.wrappee = source; // Inicializa o wrappee.
 }

 // Método writeData que delega a escrita de dados ao objeto decorado.
 @Override
 public void writeData(String data) {
     wrappee.writeData(data); // Chamada delegada.
 }

 // Método readData que delega a leitura de dados ao objeto decorado.
 @Override
 public String readData() {
     return wrappee.readData(); // Retorno delegado.
 }
}