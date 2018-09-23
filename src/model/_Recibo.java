package model;

public class _Recibo {
    private int id;
    private int id_cliente;
    private int id_pago;

    public _Recibo(int id_cliente, int id_pago) {
        this.id_cliente = id_cliente;
        this.id_pago = id_pago;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_pago() {
        return id_pago;
    }

    @Override
    public String toString() {
        return "_Recibo{" +
//                "id=" + id +
                "id_cliente=" + id_cliente +
                ", id_pago=" + id_pago +
                '}';
    }
}
