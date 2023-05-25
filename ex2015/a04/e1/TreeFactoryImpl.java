package ex2015.a04.e1;

import java.util.List;

public class TreeFactoryImpl <X> implements TreeFactory <X>{

    @Override
    public Tree <X> emptyTree() {
        return new TreeImpl<X>();
    }

    @Override
    public Tree <X> consTree(X root, List<Tree<X>> sons) {
        return new TreeImpl<X>(root, sons);
    }

}
