package br.edu.infnet.av1_web.model;


public enum StatusPedido {
    
    AGUARDANDO_CONFIRM_PAGAMENTO(1),
    PAGAMENTO_CONFIRMADO(2),
    ENTREGUE_TRANSPORTADORA(3),
    EM_TRANSITO(4),
    ENTREGUE(5);
    
    private final int val;
    
    StatusPedido(int val) {
        this.val = val;
    }
    
    public int getStatus() {
        return val;
    }
    
    public static StatusPedido fromInt(int i) {
    for (StatusPedido status : StatusPedido.values()) {
      if (status.getStatus() == i) {
        return status;
      }
    }
    return null;
  }
}