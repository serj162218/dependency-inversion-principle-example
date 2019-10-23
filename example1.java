/**
 * 實現依賴反轉的物件導向
 * 用於教學
 * #Cleancode #Java
 * #Object Oriented Programming
 */
public class main{
     public static void main(String []args){
         PenInterface redPen = new Pen("red");
         PenInterface blackPen = new Pen("black");
         PenWithEraserInterface yellowPencil = new Pencil("yellow",new Eraser(3));
         PenWithEraserInterface greenPencil = new Pencil("green",new Eraser(4));
         PenWithEraserInterface whitePencil = new Pencil("white",new Eraser(5));
         PencilBoxInterface myBox = new PencilBox();
         myBox.setPenSize(3);
         myBox.setPencilSize(5);
         myBox.putPenInBox(redPen);
         myBox.putPenInBox(blackPen);
         myBox.putPencilInBox(yellowPencil);
         myBox.putPencilInBox(greenPencil);
         myBox.putPencilInBox(whitePencil);
         myBox.usePen(0).write();
         myBox.usePencil(0).write();
         myBox.usePencil(2).useEraser();
         
         EraserInterface myRag = new Rag("blue","John");
         myRag.erase();
     }
}
interface PenInterface{
    public void write();
}
interface EraserInterface{
    public void erase();
}
interface PenWithEraserInterface extends PenInterface{
    public void useEraser();
}
interface PencilBoxInterface{
    public void putPenInBox(PenInterface pen);
    public void putPencilInBox(PenWithEraserInterface Pencil);
    public PenInterface usePen(int which);
    public PenWithEraserInterface usePencil(int which);
    public void setPenSize(int size);
    public void setPencilSize(int size);
}
class Pen implements PenInterface{
    private String color;
    Pen(String color){
        this.color = color;
    }
    public void write(){
        System.out.println(this.color + " font and can't be erased");
    }
}
class Eraser implements EraserInterface{
    private int size;
    Eraser(int size){
        this.size = size;
    }
    public void erase(){
        System.out.println("Just erase " + size + " of letters");
    }
}
class Pencil implements PenWithEraserInterface{
    private String color;
    private EraserInterface eraser;
    Pencil(String color,EraserInterface eraser){
        this.color = color;
        this.eraser = eraser;
    }
    public void write(){
        System.out.println(this.color + "font and can be erased");
    }
    public void useEraser(){
        this.eraser.erase();
    }
}
class PencilBox implements PencilBoxInterface {
    private PenInterface pen[];
    private PenWithEraserInterface pencil[];
    private int numberOfPen;
    private int numberOfPencil;
    
    PencilBox(){
        numberOfPen = 0;
        numberOfPencil = 0;
    }
    public void putPenInBox(PenInterface pen){
        this.pen[numberOfPen] = pen;
        numberOfPen++;
    }
    public void putPencilInBox(PenWithEraserInterface pencil){
        this.pencil[numberOfPencil] = pencil;
        numberOfPencil++;
    }
    public PenInterface usePen(int which){
        return this.pen[which];
    }
    public PenWithEraserInterface usePencil(int which){
        return this.pencil[which];    
    }
    public void setPenSize(int size){
        this.pen = new Pen[size];
    }
    public void setPencilSize(int size){
        this.pencil = new Pencil[size];
    }
}
class Rag implements EraserInterface{
    private String color;
    private String owner;
    Rag(String color,String owner){
        this.color = color;
        this.owner = owner;
    }
    public void erase(){
        System.out.println("Using " + owner + "'s " + color + " rag to erase");
    }
}