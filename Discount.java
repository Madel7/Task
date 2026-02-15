public interface Discount {
    double addDiscount(double total);
}

class TenPercentDiscount implements Discount {
    @Override
    public double addDiscount(double x) {
        return x * 0.9;
    }
}


class FlatFiftyDiscount implements Discount {
    @Override
    public double addDiscount(double total) {
        double result = total - 50;
        return result < 0 ? 0 : result;
    }
}


class NoDiscount implements Discount {
    @Override
    public double addDiscount(double total) {
        return total;
    }
}

