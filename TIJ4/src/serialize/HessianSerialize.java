package serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianSerialize {
public static void main(String[] args) throws Exception {
	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
	Person zhangsanPerson = new Person();
	zhangsanPerson.setName("hessian");
	hessianOutput.writeObject(zhangsanPerson);
	byte[] zhangsanByte = byteArrayOutputStream.toByteArray();
	
	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zhangsanByte);
	HessianInput hessianInput = new HessianInput(byteArrayInputStream);
	Person person = (Person) hessianInput.readObject();
	System.out.println(person.getName());
	
}
}
