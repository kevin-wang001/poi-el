package com.kvn.poi.log;

public class LogOp {
    
    public static final String HTTP_POOL_INIT = "http pool init";
    public static final String HTTP_POOL_SHUT = "http pool shutdown";
    public static final String HTTP_POOL_REG = "register connection manager";
    public static final String HTTP_POOL_CLEAN = "http pool clean";
    public static final String HTTP_CONN_SHUT_FAIL = "shutdown httpclient fail";
    public static final String HTTP_REQ_START = "http req start";
    public static final String HTTP_REQ_FINISH = "http req finish";
    public static final String HTTP_REQ_FAIL = "http req fail";
    public static final String HTTP_RESP_FAIL = "http resp fail";
    public static final String HTTP_DNS_RESOLVE_SUC = "httpclient dns resolver suc";
    public static final String HTTP_DNS_RESOLVE_FAIL = "httpclient dns resolver fail";
    
    public static final String MQ_CONSUMER_INIT = "mq.initConsumer";
    public static final String MQ_CONSUME_MSG = "mq.consumeMessage";
    public static final String MQ_CONSUME_FAIL = "mq.consume.fail";
    public static final String MQ_CONSUME_RE_CONN = "mq.consume.reconnect";
    public static final String MQ_CONSUME_HOOK = "mq.consume.hook";
    public static final String MQ_CONSUME_SHUTDOWN = "mq.consume.shutdown";
    public static final String MQ_PRODUCER_INIT = "mq.initProducer";
    public static final String MQ_PRODUCER_FAIL = "mq.producer.sendFail";
    public static final String MQ_PRODUCER_SHUTDOWN = "mq.producer.shutdown";
    public static final String MQ_DESTORY_FAIL = "mq.destory.exception";
    
    public static final String WEB_REQ = "http.req";
    public static final String WEB_RESP = "http.resp";
    public static final String WEB_REQ_TIME = "req.time";
    
    public static final String TOKEN_PARAMS = "token.params";
    public static final String TOKEN_GEN = "token.generate";
    public static final String TOKEN_CHECK = "token.check";
    
    public static final String EXP_RESOLVER_DEAL = "exception.resolver.deal";
    public static final String EXP_RESOLVER_FAIL = "exception.resolver.fail";
    public static final String EXP_NEXT_ACTION_INVALID = "exception.nextAction.invalid";
    
    public static final String DIS_SESS = "distribute.session.log";
    public static final String DIS_SESS_FAIL = "distribute.session.fail";
}
