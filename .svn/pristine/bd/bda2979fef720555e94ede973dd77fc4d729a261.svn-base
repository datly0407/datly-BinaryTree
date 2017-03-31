package structures;

import java.util.Iterator;

/**
 * BinaryTreeADT defines the interface to a binary tree data structure.
 *
 * @author Lewis and Chase
 * @author Dat Ly formatted with TCSS 342 code convention.
 * @version 02/11/2017 
 * @param <T> Generic Type. At runtime, the complier will replace it with 
 * particular object provided from the user.
 */
public interface BinaryTreeADT<T> 
{
    /** 
     * Returns a reference to the root element. 
     *
     * @return a reference to the root
     */
    T getRootElement();

    /** 
     * Returns true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty, false otherwise
     */
    boolean isEmpty();

    /** 
     * Returns the number of elements in this binary tree.
     *
     * @return the number of elements in the tree
     */
    int size();

    /** 
     * Returns true if the binary tree contains an element that matches
     * the specified element and false otherwise. 
     *
     * @param theTargetElement the element being sought in the tree
     * @return true if the tree contains the target element
     */
    boolean contains(T theTargetElement);

    /** 
     * Returns a reference to the specified element if it is found in 
     * this binary tree.  Throws an exception if the specified element
     * is not found.
     *
     * @param theTargetElement the element being sought in the tree
     * @return a reference to the specified element
     */
    T find(T theTargetElement);

    /**  
     * Returns the string representation of this binary tree.
     *
     * @return a string representation of the binary tree
     */
    String toString();

    /**  
     * Returns an iterator over the elements of this tree.
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iterator();
    
    /**  
     * Returns an iterator that represents an inorder traversal on this binary tree.  
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorInOrder();
    
    /**  
     * Returns an iterator that represents a preorder traversal on this binary tree. 
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorPreOrder();

    /**  
     * Returns an iterator that represents a postorder traversal on this binary tree. 
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorPostOrder();

    /**  
     * Returns an iterator that represents a levelorder traversal on the binary tree.
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorLevelOrder();
    
    /**
     * This method will count the number of nodes in the tree that 
     * contain exactly 2 null children. 
     * 
     * @return the number of nodes in the tree that contain 2 null children.
     */
    int countLeafNodes();
    
    /**
     * This method will count the number of nodes in the tree that 
     * contain exactly one non-null children.
     * 
     * @return the number of nodes in the tree that contain exactly 
     * one non-null children
     */
    int countOneChildNodes();
}

