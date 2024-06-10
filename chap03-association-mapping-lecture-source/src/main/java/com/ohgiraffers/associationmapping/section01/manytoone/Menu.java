package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.*;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    /* 중요!! 영속성 전이
    *       특정 엔티티를 영속화(등록) 할 떄, 연관 된 엔티티도 함께 영속화 한다는 의미이다.
    *       즉, -> menu 엔티티를 영속화 할때, Category 엔티티도 같이 영속화 시킨다.
    *  */
    @ManyToOne
    @JoinColumn(name = "category_code")     // 참고. *JoinColumn: category_code와 조인하고 있다고 설정하기_FK와 비슷한 역활(해당 연결된 카테고리를 알고 있다는 것!/전정보)
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected Menu() {}

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }

}
