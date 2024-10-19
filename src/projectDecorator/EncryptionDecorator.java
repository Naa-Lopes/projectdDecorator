package projectDecorator;

import java.util.Base64;

//Classe EncryptionDecorator que estende DataSourceDecorator, adicionando encriptação aos dados.
public class EncryptionDecorator extends DataSourceDecorator {

 // Construtor que recebe uma fonte de dados.
 public EncryptionDecorator(DataSource source) {
     super(source);
 }

 // Sobrescreve writeData para encriptar dados antes de armazená-los.
 @Override
 public void writeData(String data) {
     super.writeData(encode(data)); // Chama encode para encriptação.
 }

 // Sobrescreve readData para desencriptar dados lidos.
 @Override
 public String readData() {
     return decode(super.readData()); // Chama decode para desencriptação.
 }

 // Método privado que encripta dados, incrementando bytes e codificando em Base64.
 private String encode(String data) {
     byte[] result = data.getBytes(); // Converte a string em bytes.
     for (int i = 0; i < result.length; i++) {
         result[i] += (byte) 1; // Incrementa cada byte.
     }
     return Base64.getEncoder().encodeToString(result); // Retorna em Base64.
 }

 // Método privado que desencripta dados, decodificando de Base64 e decrementando bytes.
 private String decode(String data) {
     byte[] result = Base64.getDecoder().decode(data); // Decodifica de Base64.
     for (int i = 0; i < result.length; i++) {
         result[i] -= (byte) 1; // Decrementa cada byte.
     }
     return new String(result); // Retorna a string original.
 }
}