import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoProvider {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-provider.xml"});
        context.start();
        System.out.println("�����ṩ��ע��ɹ����˿ڣ�20880��");
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        context.close();
	}
}
