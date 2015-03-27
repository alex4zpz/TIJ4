package whoFeedsFish;

public class WhoFeedsFish
{
    private static final String problem = "爱因斯坦的推理题： "
                        +"1．有5栋5种颜色的房子 "
                        +"2．每一位房子的主人国籍都不同 "
                        +"3．这五个人每人只喝一个牌子的饮料，只抽一个牌子的香烟，只养一种宠物 "
                        +"4．没有人有相同的宠物，抽相同牌子的烟，喝相同牌子的饮料 "
                        +"已知条件： "
                        +"1．英国人住在红房子里 "
                        +"2．瑞典人养了一条狗 "
                        +"3．丹麦人喝茶 "
                        +"4．绿房子在白房子的左边 "
                        +"5．绿房子主人喝咖啡 "
                        +"6．抽pallmall烟的人养了一只鸟 "
                        +"7．黄房子主人抽dunhill烟 "
                        +"8．住在中间房子的人喝牛奶 "
                        +"9．挪威人住在第一间房子 "
                        +"10．抽混合烟的人住在养猫人的旁边 "
                        +"11．养马人住在抽dunhill烟人的旁边 "
                        +"12．抽bluemaster烟的人喝啤酒 "
                        +"13．德国人抽prince烟 "
                        +"14．挪威人住在蓝房子旁边 "
                        +"15．抽混合烟的人的邻居喝矿泉水 "
                        +"问题：谁养鱼？ ";               

    /**         
     * @return Returns the problem.         
     */
    public String getProblem()
    {
        return problem;
    }

    private static final int NATIONALITY_ENGLISH = 1;

    private static final int NATIONALITY_SWIDISH = 2;

    private static final int NATIONALITY_DAMARK = 3;

    private static final int NATIONALITY_NORWAY = 4;

    private static final int NATIONALITY_GERMAN = 5;

    private int[] nationalities = new int[5];

    private static final int COLOR_RED = 1;

    private static final int COLOR_GREEN = 2;

    private static final int COLOR_YELLOW = 3;

    private static final int COLOR_WHITE = 4;

    private static final int COLOR_BLUE = 5;

    private int[] colors = new int[5];

    private static final int PET_DOG = 1;

    private static final int PET_BIRD = 2;

    private static final int PET_CAT = 3;

    private static final int PET_HORSE = 4;

    private static final int PET_FISH = 5;

    private int[] pets = new int[5];

    private static final int DRINK_TEA = 1;

    private static final int DRINK_COFFEE = 2;

    private static final int DRINK_MILK = 3;

    private static final int DRINK_BEER = 4;

    private static final int DRINK_WATER = 5;

    private int[] drinks = new int[5];

    private static final int TOBACCO_PALLMALL = 1;

    private static final int TOBACCO_DUNHILL = 2;

    private static final int TOBACCO_BLUEMASTER = 3;

    private static final int TOBACCO_PRINCE = 4;

    private static final int TOBACCO_MIXED = 5;

    private int[] tobaccoes = new int[5];

    //5*5的二维数组，答案就在其中：        
    private int[][] key = { nationalities, colors, pets, drinks, tobaccoes };

    private static final int[][] values = { 
            { 1, 2, 3, 4, 5 },
            { 1, 2, 3, 5, 4 }, 
            { 1, 2, 4, 3, 5 }, 
            { 1, 2, 4, 5, 3 },
            { 1, 2, 5, 3, 4 }, 
            { 1, 2, 5, 4, 3 }, 
            { 1, 3, 2, 4, 5 },
            { 1, 3, 2, 5, 4 }, 
            { 1, 3, 4, 2, 5 }, 
            { 1, 3, 4, 5, 2 },
            { 1, 3, 5, 2, 4 }, 
            { 1, 3, 5, 4, 2 }, 
            { 1, 4, 2, 3, 5 },
            { 1, 4, 2, 5, 3 }, 
            { 1, 4, 3, 2, 5 }, 
            { 1, 4, 3, 5, 2 },
            { 1, 4, 5, 2, 3 }, 
            { 1, 4, 5, 3, 2 }, 
            { 1, 5, 2, 3, 4 },
            { 1, 5, 2, 4, 3 }, 
            { 1, 5, 3, 2, 4 }, 
            { 1, 5, 3, 4, 2 },
            { 1, 5, 4, 2, 3 }, 
            { 1, 5, 4, 3, 2 }, 
            { 2, 1, 3, 4, 5 },
            { 2, 1, 3, 5, 4 }, 
            { 2, 1, 4, 3, 5 }, 
            { 2, 1, 4, 5, 3 },
            { 2, 1, 5, 3, 4 }, 
            { 2, 1, 5, 4, 3 }, 
            { 2, 3, 1, 4, 5 },
            { 2, 3, 1, 5, 4 }, 
            { 2, 3, 4, 1, 5 }, 
            { 2, 3, 4, 5, 1 },
            { 2, 3, 5, 1, 4 }, 
            { 2, 3, 5, 4, 1 }, 
            { 2, 4, 1, 3, 5 },
            { 2, 4, 1, 5, 3 }, 
            { 2, 4, 3, 1, 5 }, 
            { 2, 4, 3, 5, 1 },
            { 2, 4, 5, 1, 3 }, 
            { 2, 4, 5, 3, 1 }, 
            { 2, 5, 1, 3, 4 },
            { 2, 5, 1, 4, 3 }, 
            { 2, 5, 3, 1, 4 }, 
            { 2, 5, 3, 4, 1 },
            { 2, 5, 4, 1, 3 }, 
            { 2, 5, 4, 3, 1 }, 
            { 3, 1, 2, 4, 5 },
            { 3, 1, 2, 5, 4 }, 
            { 3, 1, 4, 2, 5 }, 
            { 3, 1, 4, 5, 2 },
            { 3, 1, 5, 2, 4 }, 
            { 3, 1, 5, 4, 2 }, 
            { 3, 2, 1, 4, 5 },
            { 3, 2, 1, 5, 4 }, 
            { 3, 2, 4, 1, 5 }, 
            { 3, 2, 4, 5, 1 },
            { 3, 2, 5, 1, 4 }, 
            { 3, 2, 5, 4, 1 }, 
            { 3, 4, 1, 2, 5 },
            { 3, 4, 1, 5, 2 }, 
            { 3, 4, 2, 1, 5 }, 
            { 3, 4, 2, 5, 1 },
            { 3, 4, 5, 1, 2 }, 
            { 3, 4, 5, 2, 1 }, 
            { 3, 5, 1, 2, 4 },
            { 3, 5, 1, 4, 2 }, 
            { 3, 5, 2, 1, 4 }, 
            { 3, 5, 2, 4, 1 },
            { 3, 5, 4, 1, 2 }, 
            { 3, 5, 4, 2, 1 }, 
            { 4, 1, 2, 3, 5 },
            { 4, 1, 2, 5, 3 }, 
            { 4, 1, 3, 2, 5 }, 
            { 4, 1, 3, 5, 2 },
            { 4, 1, 5, 2, 3 }, 
            { 4, 1, 5, 3, 2 }, 
            { 4, 2, 1, 3, 5 },
            { 4, 2, 1, 5, 3 }, 
            { 4, 2, 3, 1, 5 }, 
            { 4, 2, 3, 5, 1 },
            { 4, 2, 5, 1, 3 }, 
            { 4, 2, 5, 3, 1 }, 
            { 4, 3, 1, 2, 5 },
            { 4, 3, 1, 5, 2 }, 
            { 4, 3, 2, 1, 5 }, 
            { 4, 3, 2, 5, 1 },
            { 4, 3, 5, 1, 2 }, 
            { 4, 3, 5, 2, 1 }, 
            { 4, 5, 1, 2, 3 },
            { 4, 5, 1, 3, 2 }, 
            { 4, 5, 2, 1, 3 }, 
            { 4, 5, 2, 3, 1 },
            { 4, 5, 3, 1, 2 }, 
            { 4, 5, 3, 2, 1 }, 
            { 5, 1, 2, 3, 4 },
            { 5, 1, 2, 4, 3 }, 
            { 5, 1, 3, 2, 4 }, 
            { 5, 1, 3, 4, 2 },
            { 5, 1, 4, 2, 3 }, 
            { 5, 1, 4, 3, 2 }, 
            { 5, 2, 1, 3, 4 },
            { 5, 2, 1, 4, 3 }, 
            { 5, 2, 3, 1, 4 }, 
            { 5, 2, 3, 4, 1 },
            { 5, 2, 4, 1, 3 }, 
            { 5, 2, 4, 3, 1 }, 
            { 5, 3, 1, 2, 4 },
            { 5, 3, 1, 4, 2 }, 
            { 5, 3, 2, 1, 4 }, 
            { 5, 3, 2, 4, 1 },
            { 5, 3, 4, 1, 2 }, 
            { 5, 3, 4, 2, 1 }, 
            { 5, 4, 1, 2, 3 },
            { 5, 4, 1, 3, 2 }, 
            { 5, 4, 2, 1, 3 }, 
            { 5, 4, 2, 3, 1 },
            { 5, 4, 3, 1, 2 }, 
            {5, 4, 3, 2, 1 } 
    };

    public void printKey()
    {
        for (int i = 0; i < 5; i++)
        {
            print("nationality", key[0][i]);
        }

        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            print("color", key[1][i]);
        }
        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            print("pet", key[2][i]);
        }
        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            print("drink", key[3][i]);
        }
        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            print("tobacco", key[4][i]);
        }
        System.out.println();
    }

    /**
     * 
     * @param item
     * @param index
     */
    private void print(String item, int index)
    {
        if (false)
        {
        }
        else if ("nationality".equals(item))
        {
            switch (index)
            {
            case 1:
                System.out.print("英国人 ");
                break;
            case 2:
                System.out.print("瑞典人 ");
                break;
            case 3:
                System.out.print("丹麦人 ");
                break;
            case 4:
                System.out.print("挪威人 ");
                break;
            case 5:
                System.out.print("德国人 ");
                break;
            }
        }
        else if ("color".equals(item))
        {
            switch (index)
            {
            case 1:
                System.out.print("红房子 ");
                break;
            case 2:
                System.out.print("绿房子 ");
                break;
            case 3:
                System.out.print("黄房子 ");
                break;
            case 4:
                System.out.print("白房子 ");
                break;
            case 5:
                System.out.print("蓝房子 ");
                break;
            }
        }
        else if ("pet".equals(item))
        {
            switch (index)
            {
            case 1:
                System.out.print("狗 ");
                break;
            case 2:
                System.out.print("鸟 ");
                break;
            case 3:
                System.out.print("猫 ");
                break;
            case 4:
                System.out.print("马 ");
                break;
            case 5:
                System.out.print("鱼 ");
                break;
            }
        }
        else if ("drink".equals(item))
        {
            switch (index)
            {
            case 1:
                System.out.print("茶 ");
                break;
            case 2:
                System.out.print("咖啡 ");
                break;
            case 3:
                System.out.print("牛奶 ");
                break;
            case 4:
                System.out.print("啤酒 ");
                break;
            case 5:
                System.out.print("水 ");
                break;
            }
        }
        else if ("tobacco".equals(item))
        {
            switch (index)
            {
            case 1:
                System.out.print("PALLMALL ");
                break;
            case 2:
                System.out.print("DUNHILL ");
                break;
            case 3:
                System.out.print("BLUEMASTER ");
                break;
            case 4:
                System.out.print("PRINCE ");
                break;
            case 5:
                System.out.print("混合烟 ");
                break;
            }
        }
    }

    //        条件1：英国人住在红房子里  01        
    private boolean check01()
    {
        for (int i = 0; i < nationalities.length; i++)
        {
            if (key[0][i] == NATIONALITY_ENGLISH)
            {
                if (key[1][i] != COLOR_RED)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    //        条件2：瑞典人养了一条狗  02        
    private boolean check02()
    {
        for (int i = 0; i < nationalities.length; i++)
        {
            if (key[0][i] == NATIONALITY_SWIDISH)
            {
                if (key[2][i] != PET_DOG)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    //        条件4：绿房子在白房子的左边  1        
    private boolean check1()
    {
        for (int i = 0; i < colors.length; i++)
        {
            if (key[1][i] == COLOR_GREEN)
            {
                for (int j = 0; j < colors.length; j++)
                {
                    if (key[1][j] == COLOR_WHITE)
                    {
                        if (i > j)
                        {
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //        条件8：住在中间房子的人喝牛奶  3                
    private boolean check3()
    {
        return key[3][2] == DRINK_MILK ? true : false;
    }

    //        条件9：挪威人住在第一间房子  0        
    private boolean check0()
    {
        if (key[0][0] != NATIONALITY_NORWAY)
        {
            return false;
        }
        return true;
    }

    //        14．挪威人住在蓝房子旁边  01        
    private boolean check011()
    {
        for (int i = 0; i < nationalities.length; i++)
        {
            if (key[0][i] == NATIONALITY_NORWAY)
            {
                for (int j = 0; j < colors.length; j++)
                {
                    if (key[1][j] == COLOR_BLUE)
                    {
                        if (Math.abs(i - j) == 1)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////                
    //条件1，2，3，4，8，9，14被拿出来提前检测（预检测以去除不必要的操作，减少执行时间）        
    private boolean check()
    {
        //                条件3：丹麦人喝茶  03                
        for (int i = 0; i < nationalities.length; i++)
        {
            if (key[0][i] == NATIONALITY_DAMARK)
            {
                if (key[3][i] != DRINK_TEA)
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
        }
        //                条件5：绿房子主人喝咖啡  13                
        for (int i = 0; i < colors.length; i++)
        {
            if (key[1][i] == COLOR_GREEN)
            {
                if (key[3][i] != DRINK_COFFEE)
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
        }
        //                条件6：抽pallmall烟的人养了一只鸟  24                
        for (int i = 0; i < tobaccoes.length; i++)
        {
            if (key[4][i] == TOBACCO_PALLMALL)
            {
                if (key[2][i] != PET_BIRD)
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
        }
        //                条件7：黄房子主人抽dunhill烟  14                
        for (int i = 0; i < colors.length; i++)
        {
            if (key[1][i] == COLOR_YELLOW)
            {
                if (key[4][i] != TOBACCO_DUNHILL)
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
        }
        //                条件10：抽混合烟的人住在养猫人的旁边  24                
        for (int i = 0; i < tobaccoes.length; i++)
        {
            if (key[4][i] == TOBACCO_MIXED)
            {
                for (int j = 0; j < pets.length; j++)
                {
                    if (key[2][j] == PET_CAT)
                    {
                        if (i - j != 1 && i - j != -1)
                        {
                            return false;
                        }
                        break;
                    }
                }
                break;
            }
        }
        //                条件11：养马人住在抽dunhill烟人的旁边  24                
        for (int i = 0; i < pets.length; i++)
        {
            if (key[2][i] == PET_HORSE)
            {
                for (int j = 0; j < tobaccoes.length; j++)
                {
                    if (key[4][j] == TOBACCO_DUNHILL)
                    {
                        if (i - j != 1 && i - j != -1)
                        {
                            return false;
                        }
                        break;
                    }
                }
                break;
            }
        }
        //                条件12：抽bluemaster烟的人喝啤酒  34                
        for (int i = 0; i < tobaccoes.length; i++)
        {
            if (key[4][i] == TOBACCO_BLUEMASTER)
            {
                if (key[3][i] != DRINK_BEER)
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
        }
        //                13．德国人抽prince烟  04                
        for (int i = 0; i < nationalities.length; i++)
        {
            if (key[0][i] == NATIONALITY_GERMAN)
            {
                if (key[4][i] != TOBACCO_PRINCE)
                {
                    return false;
                }
                else
                {
                    break;
                }
            }
        }
        //                15．抽混合烟的人的邻居喝矿泉水  34                
        for (int i = 0; i < tobaccoes.length; i++)
        {
            if (key[4][i] == TOBACCO_MIXED)
            {
                for (int j = 0; j < drinks.length; j++)
                {
                    if (key[3][j] == DRINK_WATER)
                    {
                        if ((i - j != 1) && (i - j != -1))
                        {
                            return false;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                break;
            }
        }
        //满足所有条件：                
        return true;
    }

    /**
     * 
     *
     */
    public void run()
    {
        int count = 0;
        for (int a = 0; a < 120; a++)
        {
            for (int i = 0; i < 5; i++)
            {
                key[0][i] = values[a][i];
            }
            if (!check0())
            {
                continue;
            }
            for (int b = 0; b < 120; b++)
            {
                for (int i = 0; i < 5; i++)
                {
                    key[1][i] = values[b][i];
                }
                if (!check01() || !check011() || !check1())
                {
                    continue;
                }
                for (int c = 0; c < 120; c++)
                {
                    for (int i = 0; i < 5; i++)
                    {
                        key[2][i] = values[c][i];
                    }
                    if (!check02())
                    {
                        continue;
                    }
                    for (int d = 0; d < 120; d++)
                    {
                        for (int i = 0; i < 5; i++)
                        {
                            key[3][i] = values[d][i];
                        }
                        if (!check3())
                        {
                            continue;
                        }
                        for (int e = 0; e < 120; e++)
                        {
                            for (int i = 0; i < 5; i++)
                            {
                                key[4][i] = values[e][i];
                            }
                            if (!check())
                            {
                                continue;
                            }
                            System.out.println("答案" + (++count));
                            printKey();
                            System.out.println("-----------------------------------------------"+ "--------------------------------------");
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        WhoFeedsFish wff = new WhoFeedsFish();
        System.out.println(wff.getProblem());
        System.out.println("========================================Start========================================");
        wff.run();
        System.out.println("========================================End==========================================");
    }
}
