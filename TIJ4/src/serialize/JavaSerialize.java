package serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class JavaSerialize {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
		Person zhangsanPerson = new Person();
		zhangsanPerson.setId(998);
		zhangsanPerson.setName("zhangsan");
		outputStream.writeObject(zhangsanPerson);
		byte[] zhangshanbyte = byteArrayOutputStream.toByteArray();
		ByteArrayInputStream isArrayInputStream = new ByteArrayInputStream(zhangshanbyte);
		ObjectInputStream inputStream = new ObjectInputStream(isArrayInputStream);
		Person person = (Person)inputStream.readObject();
		System.out.println(person.getId());
		System.out.println(person.getName());
		
	}
}
