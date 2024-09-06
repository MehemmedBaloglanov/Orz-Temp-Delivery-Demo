package com.intelliacademy.orizonroute.request;


import java.util.UUID;

public record UpdateRequest<M>(UUID targetId, M model){

}
