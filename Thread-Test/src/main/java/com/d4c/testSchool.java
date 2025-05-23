package com.d4c;



public class testSchool {
    double taxWithoutBonus(double income,int specialLimit) {
        double tax=0.0;
        double incomeInTax=income-12*(specialLimit+5000);
        if(incomeInTax<=36000) {
            tax=incomeInTax*0.03;
        }else if(incomeInTax<=144000) {
            tax=incomeInTax*0.1-2520;
        }else if(incomeInTax<=300000) {
            tax=incomeInTax*0.2-16920;
        }else tax=incomeInTax*0.45-181920;
        return tax;
    }
    //年终奖单独计算所得税
    double taxBonus(double bonus) {
        double tax=0.0;
        double bonusInTax=bonus/12;
        if(bonusInTax<=3000) {
            tax=bonus*0.03;
        }else if(bonusInTax<=12000) {
            tax=bonus*0.1-210;
        }else if(bonusInTax<=25000) {
            tax=bonus*0.2-1410;
        }else tax=bonus*0.45-15160;
        return tax;
    }
    //总所得税
    void computTax(double income,int specialLimit,double bonus) {
        double taxWithBonus=taxWithoutBonus(income-bonus,specialLimit)
                +taxBonus(bonus);
        double taxWithoutBonus=taxWithoutBonus(income,specialLimit);
        if(taxWithBonus<taxWithoutBonus) {
            System.out.println("采用奖金单独计算纳税更划算，纳税额为"+taxWithBonus);
        }else System.out.println("采用奖金合并计算纳税更划算，纳税额为"+taxWithoutBonus);
    }

    public static void main(String[] args) {

    }


}
