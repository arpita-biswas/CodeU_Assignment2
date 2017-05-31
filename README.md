# CodeU_Assignment2
Please view branch "review" for reviewing my code.  
Assumption: No duplicate elements are present in the binary tree.

Assignment 2 has two questions:
## Q1
Print Ancestors: Given a Binary Tree and a key, write a function that prints all the ancestors of the key in the given
binary tree.

If tree is empty then return the statement "Empty Tree".  
If the key is in root of the tree, there are no ancestors, hence print nothing and return.  
Process the tree in pre-order: root->left->right,
-  If root contains the key, then return true.
-  Otherwise, process the left subtree (recursive call with root.left as new root)
    - If it returns true (i.e. key is in that left subtree), then print root (since it is one of the ancestors) and return true.
    - Else, process the right subtree (recursive call with root.left as new root)
        - If it returns true (i.e. key is in that right subtree), then print root (since it is one of the ancestors) and return true.
        - Else, the subtree rooted at "root" does not contain the key, hence return false. 
        

## Q2 
Common Ancestor: Design an algorithm and write code to find the lowest common ancestor of two nodes in a binary tree.
Avoid storing additional nodes in a data structure.

Let p and q be two nodes.  
Process the tree in pre-order: root->left->right,  
- If root contains p, then search for q in the subtree rooted at "root"
    - If q is found, then return root along with a flag indicating that p is the common ancestor.
    - If q is not found, then return root along with a flag indicating that p is a potential common ancestor.
- Similarly, if root does not contain p but contains q, then search for p in the subtree rooted at "root"
    - If q is found, then return root along with a flag indicating that p is the common ancestor.
    - If q is not found, then return root along with a flag indicating that p is a potential common ancestor.
- If root contain neither p nor q, then
    - Process the left subtree,
        - If it returns true (i.e. a common ancestor is obtained), return root.left and indicator that root.left is a common ancestor.
    - Process the right subtree,
        - If it returns true (i.e. a common ancestor is obtained), return root.right and indicator that root.right is a common ancestor.
    - If both left and right subtree returns false,
        - Check if both returns a potential common ancestor (not null) then root is the common ancestor (since it separates p and q). Return root indicating that it is a common ancestor.
        - If not, check if one of them is a potential common ancestor, then root is also a potential common ancestor. Return root.
        - If none of them are potential common ancestors (both null), then the subtree with root does not contain p or q, so return null.
