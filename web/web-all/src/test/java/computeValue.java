/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/25 15:36
 * @description:
 */
class computeValue extends Exception
{
    int detail;
    computeValue(int a)
    {
        detail = a;
    }
    public String toString()
    {
        return "detail";
    }
}
class Output
{
    static void compute (int a) throws computeValue
    {
        throw new computeValue(a);
    }
    public static void main(String args[])
    {
        try
        {
            compute(3);
        }
        catch(computeValue e)
        {
            System.out.print("Exception");
        }
    }
}
