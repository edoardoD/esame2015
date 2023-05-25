package ex2015.a04.e1;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeImpl <X> implements Tree <X> {

    private  Optional<List<Tree<X>>> chiList ;
    private X root;


    public TreeImpl() {
    }

    public TreeImpl( X root, List<Tree<X>> child) {
        chiList = (child == null) ? Optional.empty(): Optional.of(child);
        this.root = root;
    }


    @Override
    public int size() {

        var result = this.chiList.get().stream().flatMap(tree-> tree.getSons().stream()).collect(Collectors.toList()).size();
         //this.chiList.stream().flatMap(tree-> tree.stream()).toList().size();
        return result;
    }


    @Override
    public X getRoot() {
       return this.root;
    }

    @Override   
    public List<Tree<X>> getSons() {
        return this.chiList.get().stream().toList();
    }

    @Override
    public boolean contains(X x) {
       return x.equals(this.root) || this.chiList.get().contains(x);
    }

    @Override
    public Tree<X> getSubTree(X node) {
        if (this.contains(node)){
            if(this.root.equals(node)){
                return this;
            }
            //il nodo è contenuto e non è root
            return this.findSubtree(node, this.chiList.get());
        }
        return null;
    }
    
    private Tree<X> findSubtree(X node, List<Tree<X>> child) {
        var newTree = child.stream().filter(tree-> tree.contains(node)).findFirst().get();
        if(!newTree.getRoot().equals(node)){
            findSubtree(node, newTree.getSons());
        }
        return newTree;
    }

    @Override
    public List<X> toList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toList'");
    }
    
}
