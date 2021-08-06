package Main;

import java.io.*;
import java.util.*;
import Main.*;

public class Main { // 수정을 요함.
	ProductMapper ProductMapper;
	Scanner scan = new Scanner(System.in);
	Main[] proList = null;
	int pronum[] = new int[6];
	// 상품이가지는 고유 변수들.
	String name;
	int price;
	int count = 0;
	int procount;

	// 상품의 갯수
	int Newcount = 0;
	int NewNumcount = 0;

	// 상품계산을 위한 변수들
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


	void InputProduct() { //내생각에는 oid가 차곡차곡 쌓이는 구조로 프로그램을 구상하면 이렇게 진행해도 크게 문제될게 없다고 판단됨.
		//아니면 각각의 oid값에 해당하는 상품의 이름, 가격, 개수를 하나하나 넘겨주는 방법도 있음.
		//그게 아니라면 객체를 넘겨주는 방식도 괜찮아 보임. 하지만 이는 방법에대한 연구가 필요할것.
		int i = 1;
		while (true) {
			if (ProductMapper.getProductForOid(i) != null) {
				proList[i].name = ProductMapper.getProductForOid(i).getName();
				proList[i].price= ProductMapper.getProductForOid(i).getPrice();
				proList[i].count= ProductMapper.getProductForOid(i).getCount();
				i++;
			}
			else { //비게되는 값이 있다면 stop!
				break;
			}
		}
	}
	
	// 재고 출력
	void printProductNumber() {
		for (int i = 0; i < Newcount; i++) {
			System.out.println((i + 1) + ". " + "상품명: " + proList[i].name + "\t\t개수: " + pronum[i]);
		}

	}

	// 물품 입고
	int plusProductNumber(int oid, int whatCount) { //oid는 해당 상품의 값을 가져오도록. 그리고 whatCount는 추가하는 상품의 개수.
		 int newCount = ProductMapper.getProductForOid(oid).getCount()+whatCount;
		 ProductMapper.updateProductForCount(oid, newCount);
		 return newCount;
	}

	// 물품 반품. 위와 마찬가지이고 넘겨줘야하는 변수는 상품번호, 그리고 상품의 반품개수.
	int deleteProductNumber(int oid, int whatCount) {
		 int newCount = ProductMapper.getProductForOid(oid).getCount()+whatCount;
		 ProductMapper.updateProductForCount(oid, newCount);
		 return newCount;
	}

	void fullPrice() {
		System.out.println("구매하신 총금액은 " + fullcount + "원 입니다.");
		NewBill = fullcount;
		while (true) {
			System.out.println("결제금액을 입금해주세요!");
			System.out.println("결제금액:");
			int Bill = scan.nextInt();
			Kbill += Bill;
			if (NewBill - Bill > 0) {
				System.out.println("결제금액에서 " + (NewBill - Bill) + "원이 부족합니다.");
				NewBill -= Bill;
			} else
				break;
		}
		System.out.println("======== 영 수 증 ========");
		for (int i = 0; i < Newcount; i++)
			if (proList[i].count > 0) {
				System.out.println(proList[i].name + "\t" + proList[i].count + "개" + "\t"
						+ (proList[i].count) * (proList[i].price) + "원");
			}
		System.out.println("======================");
		System.out.println("총구매금액 \t\t" + fullcount + "원");
		System.out.println("받은금액 \t\t" + Kbill + "원");
		System.out.println("거스름돈\t\t" + (Kbill - fullcount) + "원\n\n\n");
	}

	void Print() {
		System.out.println("======== 상품 메뉴 ========");
		for (int i = 0; i < Newcount; i++) {
			System.out.printf("%d", i + 1);
			System.out.printf(".%s\t\t%d원\n", proList[i].name, proList[i].price);
		}
	}

	void firstCount() {
		while (pronum[id] > 0) {
			System.out.println("어떤상품을 구매하시겠습니까?");
			id = scan.nextInt();
			id--;
			System.out.println(proList[id].name + "를 선택하셨습니다.");
			if (pronum[id] <= 0) {
				System.out.println("선택하신 상품의 재고가 없습니다. 다시 선택해주세요.");
				break;
			}
			System.out.println("단가는 " + proList[id].price + "원 입니다.");
			System.out.println("몇개를 구입하시겠습니까?");
			Pcount = scan.nextInt();
			System.out.println("구매하신 금액은 " + proList[id].price * Pcount + "원 입니다.");
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
			System.out.println("더 구매하시겠습니까? (예: 1/Y, 아니오: 0/N)");
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
