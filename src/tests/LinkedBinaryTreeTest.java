/**
 * TCSS 342 - Assignment 3 
 * Winter 2017
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import structures.LinkedBinaryTree;

/**
 * Unit-testing for countLeafNodes and countOneChildNodes method inside 
 * LinkedBinaryTree class.
 * @author Dat Ly datly@uw.edu
 * @version 02/11/2017
 *
 */
public class LinkedBinaryTreeTest
{

    /**
     * Test method for countLeafNodes when the tree contain 
     * only root node. 
     */
    @Test
    public void testCountLeafNodesWithOnlyRootNode()
    {
        final LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>(4);
        
        assertEquals("Node that doesn't contain any child are leaves.", 
                     1, tree.countLeafNodes());  
    }

    /**
     * Test method for countOneChildNode when the tree contain only 
     * root node. 
     */
    @Test
    public void testCountOneChildNodesWithOnlyRootNode()
    {
        final LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>(4);
        
        assertEquals("Count node that contain exactly 1 child", 
                     0, tree.countOneChildNodes());
    }
    
    /**
     * Test method for countLeafNodes when the tree is empty. 
     */
    @Test 
    public void testCountLeafNodesWithEmptyTree() 
    {
        final LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        
        assertEquals("Empty tree shouldn't contain any leaf node", 
                     0, tree.countLeafNodes());
    }
    
    /**
     * Test method for countOneCHildNodes when the tree is empty.
     */
    @Test
    public void testCountOneChildNodesWithEmptyTree() 
    {
        final LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        
        assertEquals("Empty tree shouldn't have any node.", 0, tree.countOneChildNodes());
    }
    
    /**
     * Test method for countLeafNodes with the tree that contains 
     * only left child.
     */
    @Test
    public void testCountLeafNodesWithLeftChildOnly() 
    {
        final LinkedBinaryTree<Integer> leftMostChild = new LinkedBinaryTree<>(8);
        final LinkedBinaryTree<Integer> leafNode2 = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> leftNextChild = 
                                   new LinkedBinaryTree<>(7, leftMostChild, leafNode2);
        final LinkedBinaryTree<Integer> rightNextChild = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> leftChild = 
                                   new LinkedBinaryTree<>(6, leftNextChild, rightNextChild);
        final LinkedBinaryTree<Integer> rightChild = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> tree = 
                                   new LinkedBinaryTree<>(5, leftChild, rightChild);
        
        assertEquals("Only one leaf node at the far left.", 1, tree.countLeafNodes());
    }
    
    /**
     * Test method for countOneChildNodes with the tree that contains 
     * only left child.
     */
    @Test
    public void testCountOneChildNodesWithLeftChildOnly() 
    {
        final LinkedBinaryTree<Integer> leftMostChild = new LinkedBinaryTree<>(8);
        final LinkedBinaryTree<Integer> rightMostChild = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> leftNextChild = 
                                new LinkedBinaryTree<>(7, leftMostChild, rightMostChild);
        final LinkedBinaryTree<Integer> rightNextChild = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> leftChild = 
                                   new LinkedBinaryTree<>(6, leftNextChild, rightNextChild);
        final LinkedBinaryTree<Integer> rightChild = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> tree = 
                                   new LinkedBinaryTree<>(5, leftChild, rightChild);
        
        assertEquals("Count nodes contain only one child", 3, tree.countOneChildNodes());
    }
    
    /**
     * Test method for countLeafNodes with tree that each node 
     * other than leaves node contain exactly two children. (Full Tree)
     */
    @Test
    public void testCountLeafNodesWithFullTree() 
    {
        // Left sub-tree
        final LinkedBinaryTree<Integer> leafNode1 = new LinkedBinaryTree<>(1);
        final LinkedBinaryTree<Integer> leafNode2 = new LinkedBinaryTree<>(2);
        final LinkedBinaryTree<Integer> leftChild = 
                                   new LinkedBinaryTree<>(5, leafNode1, leafNode2);
        // Right sub-tree
        final LinkedBinaryTree<Integer> leafNode3 = new LinkedBinaryTree<>(3);
        final LinkedBinaryTree<Integer> leafNode4 = new LinkedBinaryTree<>(4);
        final LinkedBinaryTree<Integer> rightChild = 
                                   new LinkedBinaryTree<>(6, leafNode3, leafNode4);
        // Connect two sub-tree to the root node
        final LinkedBinaryTree<Integer> tree = 
                                   new LinkedBinaryTree<>(7, leftChild, rightChild);
        
        assertEquals("All leaves nodes inside full tree are at height 0", 
                     4, tree.countLeafNodes());
    }
    
    /**
     * Test method for countOneChildNodes with tree that each node other than 
     * leaves node contain exactly two children. (Full Tree)
     */
    @Test 
    public void testCountOneChildNodesWithFullTree()
    {
        // Left sub-tree
        final LinkedBinaryTree<Integer> leafNode1 = new LinkedBinaryTree<>(1);
        final LinkedBinaryTree<Integer> leafNode2 = new LinkedBinaryTree<>(2);
        final LinkedBinaryTree<Integer> leftChild = 
                                   new LinkedBinaryTree<>(5, leafNode1, leafNode2);
        // Right sub-tree
        final LinkedBinaryTree<Integer> leafNode3 = new LinkedBinaryTree<>(3);
        final LinkedBinaryTree<Integer> leafNode4 = new LinkedBinaryTree<>(4);
        final LinkedBinaryTree<Integer> rightChild = 
                                   new LinkedBinaryTree<>(6, leafNode3, leafNode4);
        // Connect two sub-tree to the root node
        final LinkedBinaryTree<Integer> tree = 
                                   new LinkedBinaryTree<>(7, leftChild, rightChild);
        
        assertEquals("There shouldn't be any contain one child in full tree", 
                     0, tree.countOneChildNodes());
    }
    
    /**
     * Test method for countLeafNodes with a complete tree.
     */
    @Test
    public void testCountLeafNodesWithCompleteTree()
    {
        // Left sub-tree
        final LinkedBinaryTree<Integer> leafNode = new LinkedBinaryTree<>(1);
        final LinkedBinaryTree<Integer> leafNode2 = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> leftChild = 
                        new LinkedBinaryTree<>(2, leafNode, leafNode2);
        // Right sub-tree
        final LinkedBinaryTree<Integer> rightChild = new LinkedBinaryTree<>(3);
        // Root of the tree
        final LinkedBinaryTree<Integer> tree = 
                        new LinkedBinaryTree<>(4, leftChild, rightChild);
        
        assertEquals("Test Case for complete tree", 2, tree.countLeafNodes());       
    }
    
    /**
     * Test method for countOneChildNodes with a complete tree.
     */
    @Test
    public void testCountOneChildNodesWithCompleteTree()
    {
        // Left sub-tree
        final LinkedBinaryTree<Integer> leafNode = new LinkedBinaryTree<>(1);
        final LinkedBinaryTree<Integer> leafNode2 = new LinkedBinaryTree<>();
        final LinkedBinaryTree<Integer> leftChild = 
                        new LinkedBinaryTree<>(2, leafNode, leafNode2);
        // Right sub-tree
        final LinkedBinaryTree<Integer> rightChild = new LinkedBinaryTree<>(3);
        // Root of the tree
        final LinkedBinaryTree<Integer> tree = 
                        new LinkedBinaryTree<>(4, leftChild, rightChild);
        
        assertEquals("Test Case for complete tree", 1, tree.countOneChildNodes());   
    }
}
