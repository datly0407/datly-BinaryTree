package structures;

/**
 * TCSS 342 - Assignment 3 
 * Winter 2017 
 */

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedBinaryTree implements the BinaryTreeADT interface.
 * 
 * @author Lewis and Chase
 * @author Dat Ly datly@uw.edu formatted with TCSS 342 code convention 
 * and added two more new methods.
 * @version 02/11/2017
 * @param <T> Generic Type
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>, Iterable<T>
{
    /**
     * Instance field that contain the reference of the 
     * root's node in a tree.
     */
    protected BinaryTreeNode<T> myRoot;   
    /**
     * Keep track when something added to the tree this variable 
     * will increment.
     */
    protected int myModCount;
    /**
     * Reference to the left child of the root.
     */
    private LinkedBinaryTree<T> myLeftSubTree;
    /**
     * Reference to the right child of the root.
     */
    private LinkedBinaryTree<T> myRightSubTree;
    
    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() 
    {
        myRoot = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param theElement the element that will become the root of the binary tree
     */
    public LinkedBinaryTree(final T theElement) 
    {
        myRoot = new BinaryTreeNode<T>(theElement);
    }
    
    /**
     * Creates a binary tree with the specified element as its root and the 
     * given trees as its left child and right child.
     *
     * @param theElement the element that will become the root of the binary tree
     * @param theLeft the left subtree of this tree
     * @param theRight the right subtree of this tree
     */
    public LinkedBinaryTree(final T theElement, final LinkedBinaryTree<T> theLeft, 
                            final LinkedBinaryTree<T> theRight) 
    {
        myRoot = new BinaryTreeNode<T>(theElement);
        myLeftSubTree = theLeft;
        myRightSubTree = theRight;
        myRoot.setLeft(theLeft.myRoot);
        myRoot.setRight(theRight.myRoot);
    }
    
    /**
     * Returns a reference to the element at the root.
     *
     * @return a reference to the specified target
     * @throws EmptyCollectionException if the tree is empty
     */
    public T getRootElement() throws EmptyCollectionException
    {
        
        return myRoot.getElement();
    }
    
    /**
     * Returns a reference to the node at the root.
     *
     * @return a reference to the specified node
     * @throws EmptyCollectionException if the tree is empty
     */
    protected BinaryTreeNode<T> getRootNode() throws EmptyCollectionException
    {
        return myRoot;
    }
    
    /**
     * Returns the left subtree of the root of this tree.
     *
     * @return a link to the left subtree of the tree
     */
    public LinkedBinaryTree<T> getLeft()
    {
        return myLeftSubTree;           
    }
    
    /**
     * Returns the right subtree of the root of this tree.
     *
     * @return a link to the right subtree of the tree
     */
    public LinkedBinaryTree<T> getRight()
    {
        return myRightSubTree;
    }
    
    /**
     * Returns true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty, false otherwise
     */
    public boolean isEmpty() 
    {
        return myRoot == null;
    }

    /**
     * Returns the integer size of this tree.
     *
     * @return the integer size of the tree
     */
    public int size() 
    {
        return 1 + myRoot.numChildren();
    }
    
    /**
     * Returns the height of this tree.
     *
     * @return the height of the tree
     */
    public int getHeight()
    {
        return height(myRoot);
    }
    
    /**
     * Returns the height of the specified node.
     *
     * @param theNode the node from which to calculate the height
     * @return the height of the tree
     */
    private int height(final BinaryTreeNode<T> theNode) 
    {
        
        int heightOfTree = 0;
        if (theNode == null)
        {
            heightOfTree = 0;
        } 
        else if (theNode.getLeft() == null && theNode.getRight() == null) 
        {
            heightOfTree = 1; 
        }
        else
        {
            final int leftSubTree = height(theNode.getLeft()) + 1;
            final int rightSubTree = height(theNode.getRight()) + 1;
            heightOfTree = rightSubTree;
            if (leftSubTree > rightSubTree) 
            {
                heightOfTree = leftSubTree;
            }
        }
        return heightOfTree;
    }
    
    /**
     * Returns true if this tree contains an element that matches the
     * specified target element and false otherwise.
     *
     * @param theTargetElement the element being sought in this tree
     * @return true if the element in is this tree, false otherwise
     */
    public boolean contains(final T theTargetElement) 
    {
        
        return checkNodes(myRoot, theTargetElement);
    }
    
    /**
     * Recursive method that will traverse through the tree 
     * and look for target element.
     * @param theRoot the root for each sub-tree inside recursive method.
     * @param theTargetElement the target element to look for inside a tree.
     * @return true if the tree contain target element and false otherwise.
     */
    private boolean checkNodes(final BinaryTreeNode<T> theRoot, 
                               final T theTargetElement) 
    {
        
        boolean containsElement = true;
        if (theRoot == null) 
        {
            containsElement = false;
        } 
        else if (theRoot.getElement().equals(theTargetElement)) 
        {
            containsElement = true;
        } 
        else 
        {
            containsElement = checkNodes(theRoot.getLeft(), theTargetElement) 
                            || checkNodes(theRoot.getRight(), theTargetElement);
        }   
        return containsElement;
    }
    
    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.  Throws a ElementNotFoundException if
     * the specified target element is not found in the binary tree.
     *
     * @param theTargetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if the element is not in the tree
     */
    public T find(final T theTargetElement) throws ElementNotFoundException
    {
        final BinaryTreeNode<T> current = findNode(theTargetElement, myRoot);
        
        if (current == null)
        {
            throw new ElementNotFoundException("LinkedBinaryTree");
        }
        
        return current.getElement();
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param theTargetElement the element being sought in this tree
     * @param theNext the element to begin searching from
     * 
     * @return the reference to target element 
     * if the method encountered this element in the tree.
     */
    private BinaryTreeNode<T> findNode(final T theTargetElement, 
                                        final BinaryTreeNode<T> theNext)
    {
        BinaryTreeNode<T> targetNode = new BinaryTreeNode<T>(null);
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(null);
        if (theNext != null)
        {
            temp = findNode(theTargetElement, theNext.getLeft());
        }
        if (theNext != null && theNext.getElement().equals(theTargetElement)) 
        {
            targetNode = theNext;
        }
        if (temp == null) 
        {
            temp = findNode(theTargetElement, theNext.getRight());
            targetNode = temp;
        }
        return targetNode;
    }
    
    /**
     * Returns a string representation of this binary tree showing
     * the nodes in an inorder fashion.
     *
     * @return a string representation of this binary tree
     */
    public String toString() 
    {
        final StringBuffer sb = new StringBuffer();
        final Iterator<T> itr = iteratorInOrder();
        while (itr.hasNext()) 
        {
            sb.append(' ');
            sb.append(itr.next());        
        }
        return sb.toString();
    }

    /**
     * Returns an iterator over the elements in this tree using the 
     * iteratorInOrder method.
     *
     * @return an in order iterator over this binary tree
     */
    public Iterator<T> iterator()
    {
        return iteratorInOrder();
    }
    
    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with
     * the root.
     *
     * @return an in order iterator over this binary tree
     */
    public Iterator<T> iteratorInOrder()
    {
        final ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inOrder(myRoot, tempList);
        
        return new TreeIterator(tempList.iterator());
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param theNode the node to be used as the root for this traversal
     * @param theTempList the temporary list for use in this traversal
     */
    protected void inOrder(final BinaryTreeNode<T> theNode, 
                           final ArrayUnorderedList<T> theTempList) 
    {
        if (theNode != null)
        {
            inOrder(theNode.getLeft(), theTempList);
            theTempList.addToRear(theNode.getElement());
            inOrder(theNode.getRight(), theTempList);
        }
    }

    /**
     * Performs an preorder traversal on this binary tree by calling 
     * an overloaded, recursive preorder method that starts with
     * the root.
     *
     * @return a pre order iterator over this tree
     */
    public Iterator<T> iteratorPreOrder() 
    {
        
        final ArrayUnorderedList<T> preOrderList = new ArrayUnorderedList<>();
        preOrder(myRoot, preOrderList);
        
        return new TreeIterator(preOrderList.iterator());
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param theNode the node to be used as the root for this traversal
     * @param theTempList the temporary list for use in this traversal
     */
    protected void preOrder(final BinaryTreeNode<T> theNode, 
                            final ArrayUnorderedList<T> theTempList) 
    {
        
        if (theNode != null)
        {
            theTempList.addToRear(theNode.getElement());
            preOrder(theNode.getLeft(), theTempList);
            preOrder(theNode.getRight(), theTempList);
        }
    }

    /**
     * Performs an postorder traversal on this binary tree by calling
     * an overloaded, recursive postorder method that starts
     * with the root.
     *
     * @return a post order iterator over this tree
     */
    public Iterator<T> iteratorPostOrder() 
    {
        
        final ArrayUnorderedList<T> postOrderList = new ArrayUnorderedList<>();
        postOrder(myRoot, postOrderList);
        
        return new TreeIterator(postOrderList.iterator());
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param theNode the node to be used as the root for this traversal
     * @param theTempList the temporary list for use in this traversal
     */
    protected void postOrder(final BinaryTreeNode<T> theNode, 
                             final ArrayUnorderedList<T> theTempList) 
    {
        
        if (theNode != null) 
        {
            postOrder(theNode.getLeft(), theTempList);
            postOrder(theNode.getRight(), theTempList);
            theTempList.addToRear(theNode.getElement());
        }
    }

    /**
     * Performs a levelorder traversal on this binary tree, using a
     * templist.
     *
     * @return a levelorder iterator over this binary tree
     */
    public Iterator<T> iteratorLevelOrder() 
    {
        final ArrayUnorderedList<BinaryTreeNode<T>> nodes = 
                              new ArrayUnorderedList<BinaryTreeNode<T>>();
        final ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        BinaryTreeNode<T> current;

        nodes.addToRear(myRoot);
        
        while (!nodes.isEmpty()) 
        {
            current = nodes.removeFirst();
            
            if (current == null)
            {
                tempList.addToRear(null);
            }
            else
            {
                tempList.addToRear(current.getElement());
                if (current.getLeft() != null)
                {
                    nodes.addToRear(current.getLeft());
                }
                if (current.getRight() != null)
                {
                    nodes.addToRear(current.getRight());
                }
            }
        }
        
        return new TreeIterator(tempList.iterator());
    }
    
    /**
     * Count number of leaf nodes in a particular tree.
     * 
     * @return the amount of leaf nodes.
     */
    public int countLeafNodes() 
    {
        
        return countLeaf(myRoot);
    }
    
    /**
     * Recursive method that will return the total amount of leaf nodes 
     * of a tree.
     * @param theNode the root of each sub-tree.
     * @return the total amount of leaf nodes.
     */
    private int countLeaf(final BinaryTreeNode<T> theNode) 
    {
        
        int total = 0;
        if (theNode == null)  
        {
            total = 0;
        } 
        else if (theNode.getLeft() == null && theNode.getRight() == null)
        {
            total = 1;
        } 
        else 
        {
            total = countLeaf(theNode.getLeft()) + countLeaf(theNode.getRight());
        }
        
        return total;
    }
    /**
     * Count number of nodes that contain exactly one child in a particular tree.
     * 
     * @return the amount of nodes that contain only one child. 
     */
    public int countOneChildNodes() 
    {
     
        return countOneChild(myRoot);
    }
    
    /**
     * Recursive helper method that will traverse through the tree 
     * and count each node that contain exactly one child.
     * 
     * @param theNode the root of each sub-tree.
     * @return the amount of node that has exactly one child.
     */
    private int countOneChild(final BinaryTreeNode<T> theNode) 
    {
        
        int total = 0; 
        if (theNode == null) 
        {
            total = 0;
        } 
        else if ((theNode.getLeft() == null || theNode.getRight() == null) 
                     && !(theNode.getLeft() == null && theNode.getRight() == null)) 
        {
            total = 1 + countOneChild(theNode.getLeft()) 
                    + countOneChild(theNode.getRight());
        } 
        else 
        {
            total = countOneChild(theNode.getLeft()) + countOneChild(theNode.getRight());
        }
        
        return total;
    }
    /**
     * Inner class to represent an iterator over the elements of this tree.
     */
    private class TreeIterator implements Iterator<T>
    {
        /**
         * Keep track of the collection, set equal to myModCount instance field
         * of outer class.
         */
        private final int myExpectedModCount;
        /**
         * Using Iterator to traverse through the tree. 
         */
        private final Iterator<T> myIter;
        
        /**
         * Sets up this iterator using the specified iterator.
         *
         * @param theIter the list iterator created by a tree traversal
         */
        TreeIterator(final Iterator<T> theIter)
        {
            this.myIter = theIter;
            myExpectedModCount = myModCount;
        }
        
        /**
         * Returns true if this iterator has at least one more element
         * to deliver in the iteration.
         *
         * @return  true if this iterator has at least one more element to deliver
         *          in the iteration
         * @throws  ConcurrentModificationException if the collection has changed
         *          while the iterator is in use
         */
        public boolean hasNext() throws ConcurrentModificationException
        {
            if (!(myModCount == myExpectedModCount)) 
            {
                throw new ConcurrentModificationException();
            }
            return myIter.hasNext();
        }
        
        /**
         * Returns the next element in the iteration. If there are no
         * more elements in this iteration, a NoSuchElementException is
         * thrown.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iterator is empty
         */
        public T next() throws NoSuchElementException
        {
            if (hasNext())
            {
                return myIter.next();
            }
            else 
            {
                throw new NoSuchElementException();
            }
        }
        
        /**
         * The remove operation is not supported.
         * 
         * @throws UnsupportedOperationException if the remove operation is called
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}

