package projectDecorator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

//Classe CompressionDecorator que estende DataSourceDecorator, adicionando compress�o aos dados.
public class CompressionDecorator extends DataSourceDecorator {
 
 // N�vel de compress�o padr�o
 private int compLevel = 6;

 // Construtor que recebe uma fonte de dados.
 public CompressionDecorator(DataSource source) {
     super(source);
 }

 // Obt�m o n�vel de compress�o atual.
 public int getCompressionLevel() {
     return compLevel;
 }

 // Define um novo n�vel de compress�o.
 public void setCompressionLevel(int value) {
     compLevel = value;
 }

 // Sobrescreve writeData para comprimir dados antes de escrev�-los.
 @Override
 public void writeData(String data) {
     super.writeData(compress(data));
 }

 // Sobrescreve readData para descomprimir dados lidos.
 @Override
 public String readData() {
     return decompress(super.readData());
 }

 // M�todo para comprimir dados, retornando uma string em Base64.
 private String compress(String stringData) {
     byte[] data = stringData.getBytes();
     try {
         ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
         DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
         dos.write(data);
         dos.close();
         return Base64.getEncoder().encodeToString(bout.toByteArray());
     } catch (IOException ex) {
         return null;
     }
 }

 // M�todo para descomprimir dados, retornando uma string original.
 private String decompress(String stringData) {
     byte[] data = Base64.getDecoder().decode(stringData);
     try {
         InputStream in = new ByteArrayInputStream(data);
         InflaterInputStream iin = new InflaterInputStream(in);
         ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
         int b;
         while ((b = iin.read()) != -1) {
             bout.write(b);
         }
         return new String(bout.toByteArray());
     } catch (IOException ex) {
         return null;
     }
 }
}