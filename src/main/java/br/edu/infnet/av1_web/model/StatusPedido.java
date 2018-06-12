package br.edu.infnet.av1_web.model;


public enum StatusPedido {
    
    AGUARDANDO_CONFIRM_PAGAMENTO,
    PAGAMENTO_CONFIRMADO,
    ENTREGUE_TRANSPORTADORA,
    EM_TRANSITO,
    ENTREGUE;
}