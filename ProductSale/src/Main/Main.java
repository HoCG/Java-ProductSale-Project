package Main;

import java.io.*;
import java.util.*;
import Main.*;

public class Main { // ������ ����.
	ProductMapper ProductMapper;
	Scanner scan = new Scanner(System.in);
	Main[] proList = null;
	int pronum[] = new int[6];
	// ��ǰ�̰����� ���� ������.
	String name;
	int price;
	int count = 0;
	int procount;

	// ��ǰ�� ����
	int Newcount = 0;
	int NewNumcount = 0;

	// ��ǰ����� ���� ������
	int fullcount = 0;
	int id;
	int Pcount;
	int Bill;
	int NewBill;
	int Kbill;
	public static void main(String[] args){
		Main a = new Main();
		a.InputProduct();
		a.printProductNumber();
	}


	void InputProduct() { //���������� oid�� �������� ���̴� ������ ���α׷��� �����ϸ� �̷��� �����ص� ũ�� �����ɰ� ���ٰ� �Ǵܵ�.
		//�ƴϸ� ������ oid���� �ش��ϴ� ��ǰ�� �̸�, ����, ������ �ϳ��ϳ� �Ѱ��ִ� ����� ����.
		//�װ� �ƴ϶�� ��ü�� �Ѱ��ִ� ��ĵ� ������ ����. ������ �̴� ��������� ������ �ʿ��Ұ�.
		int i = 1;
		while (true) {
			if (ProductMapper.getProductForOid(i) != null) {
				proList[i].name = ProductMapper.getProductForOid(i).getName();
				proList[i].price= ProductMapper.getProductForOid(i).getPrice();
				proList[i].count= ProductMapper.getProductForOid(i).getCount();
				i++;
			}
			else { //��ԵǴ� ���� �ִٸ� stop!
				break;
			}
		}
	}
	
	// ��� ���
	void printProductNumber() {
		for (int i = 0; i < Newcount; i++) {
			System.out.println((i + 1) + ". " + "��ǰ��: " + proList[i].name + "\t\t����: " + pronum[i]);
		}

	}

	// ��ǰ �԰�
	int plusProductNumber(int oid, int whatCount) { //oid�� �ش� ��ǰ�� ���� ����������. �׸��� whatCount�� �߰��ϴ� ��ǰ�� ����.
		 int newCount = ProductMapper.getProductForOid(oid).getCount()+whatCount;
		 ProductMapper.updateProductForCount(oid, newCount);
		 return newCount;
	}

	// ��ǰ ��ǰ. ���� ���������̰� �Ѱ�����ϴ� ������ ��ǰ��ȣ, �׸��� ��ǰ�� ��ǰ����.
	int deleteProductNumber(int oid, int whatCount) {
		 int newCount = ProductMapper.getProductForOid(oid).getCount()+whatCount;
		 ProductMapper.updateProductForCount(oid, newCount);
		 return newCount;
	}

	void fullPrice() {
		System.out.println("�����Ͻ� �ѱݾ��� " + fullcount + "�� �Դϴ�.");
		NewBill = fullcount;
		while (true) {
			System.out.println("�����ݾ��� �Ա����ּ���!");
			System.out.println("�����ݾ�:");
			int Bill = scan.nextInt();
			Kbill += Bill;
			if (NewBill - Bill > 0) {
				System.out.println("�����ݾ׿��� " + (NewBill - Bill) + "���� �����մϴ�.");
				NewBill -= Bill;
			} else
				break;
		}
		System.out.println("======== �� �� �� ========");
		for (int i = 0; i < Newcount; i++)
			if (proList[i].count > 0) {
				System.out.println(proList[i].name + "\t" + proList[i].count + "��" + "\t"
						+ (proList[i].count) * (proList[i].price) + "��");
			}
		System.out.println("======================");
		System.out.println("�ѱ��űݾ� \t\t" + fullcount + "��");
		System.out.println("�����ݾ� \t\t" + Kbill + "��");
		System.out.println("�Ž�����\t\t" + (Kbill - fullcount) + "��\n\n\n");
	}

	void Print() {
		System.out.println("======== ��ǰ �޴� ========");
		for (int i = 0; i < Newcount; i++) {
			System.out.printf("%d", i + 1);
			System.out.printf(".%s\t\t%d��\n", proList[i].name, proList[i].price);
		}
	}

	void firstCount() {
		while (pronum[id] > 0) {
			System.out.println("���ǰ�� �����Ͻðڽ��ϱ�?");
			id = scan.nextInt();
			id--;
			System.out.println(proList[id].name + "�� �����ϼ̽��ϴ�.");
			if (pronum[id] <= 0) {
				System.out.println("�����Ͻ� ��ǰ�� ��� �����ϴ�. �ٽ� �������ּ���.");
				break;
			}
			System.out.println("�ܰ��� " + proList[id].price + "�� �Դϴ�.");
			System.out.println("��� �����Ͻðڽ��ϱ�?");
			Pcount = scan.nextInt();
			System.out.println("�����Ͻ� �ݾ��� " + proList[id].price * Pcount + "�� �Դϴ�.");
			fullcount += proList[id].price * Pcount;
			proList[id].count += Pcount;
			pronum[id] -= Pcount;
			break;
		}
	}

	void mymain() {
		while (true) {
			Print();
			firstCount();
			System.out.println("�� �����Ͻðڽ��ϱ�? (��: 1/Y, �ƴϿ�: 0/N)");
			String kwd = scan.next();
			if (kwd.equals("Y"))
				continue;
			if (kwd.equals("1"))
				continue;
			else if (kwd.equals("N")) {
				fullPrice();
				break;
			} else if (kwd.equals("0")) {
				fullPrice();
				break;
			}
		}
	}
}
