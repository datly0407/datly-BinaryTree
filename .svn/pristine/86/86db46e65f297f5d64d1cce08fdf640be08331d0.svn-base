package structures;

import exceptions.ElementNotFoundException;

/**
 * ArrayUnorderedList represents an array implementation of an unordered list.
 *
 * @author Lewis and Chase
 * @author Dat Ly formatted in the way that yield TCSS 342 code convention.
 * @version 02/11/2017
 * @param <T> Generic Type
 */
public class ArrayUnorderedList<T> extends ArrayList<T> 
         implements UnorderedListADT<T>
{
    /**
     * Creates an empty list using the default capacity.
     */
    public ArrayUnorderedList()
    {
        super();
    }

    /**
     * Creates an empty list using the specified capacity.
     *
     * @param theInitialCapacity the intial size of the list
     */
    public ArrayUnorderedList(final int theInitialCapacity)
    {
        super(theInitialCapacity);
    }

    /**
     * Adds the specified element to the front of this list.
     * 
     * @param theElement the element to be added to the front of the list
     */
    public void addToFront(final T theElement)
    {
        if (size() == myList.length)
        {
            expandCapacity();
        }

        // shift elements up one 
        System.arraycopy(myList, 0, myList, 1, myList.length);
   
        myList[0] = theElement;
        myRear++;
        myModCount++;
    }

    /**
     * Adds the specified element to the rear of this list.
     *
     * @param theElement the element to be added to the list
     */
    public void addToRear(final T theElement)
    {
        if (size() == myList.length) 
        {
            expandCapacity();
        }
        myList[myRear] = theElement;
        myRear++;
        myModCount++;
    }

    /**
     * Adds the specified element after the specified target element.
     * Throws an ElementNotFoundException if the target is not found.
     *
     * @param theElement the element to be added after the target element
     * @param theTarget  the target that the element is to be added after
     */
    public void addAfter(final T theElement, final T theTarget)
    {
        if (size() == myList.length)
        {
            expandCapacity();
        }
        
        int scan = 0;
        // find the insertion point
        while (scan < myRear && !theTarget.equals(myList[scan]))  
        {
            scan++;
        }
        
        if (scan == myRear) 
        {
            throw new ElementNotFoundException("UnorderedList");
        }
        scan++;
        
        // shift elements up one
        System.arraycopy(myList, scan, myList, scan + 1, myList.length - scan);
          
        // insert element
        myList[scan] = theElement;
        myRear++;
        myModCount++;
    }
}
