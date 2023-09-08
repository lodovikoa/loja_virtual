package br.com.lodoviko.loja_virtual_mentoria.service;

import br.com.lodoviko.loja_virtual_mentoria.model.AcessoEndPoint;
import br.com.lodoviko.loja_virtual_mentoria.repository.AcessoEndPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AcessoEndPointService {

    @Autowired
    private AcessoEndPointRepository acessoEndPointRepository;

    private AcessoEndPoint salvar(AcessoEndPoint acessoEndPoint) {
        return acessoEndPointRepository.save(acessoEndPoint);
    }

    public AcessoEndPoint adicionarAcesso(String endPoint) {
        AcessoEndPoint acessoEndPoint = acessoEndPointRepository.findByNomeEndPoint(endPoint);

        if(acessoEndPoint == null) {
            acessoEndPoint = acessoEndPointRepository.save(new AcessoEndPoint(null,endPoint,1, LocalDateTime.now()));
        } else {
            acessoEndPoint.setQtdeAcessos(acessoEndPoint.getQtdeAcessos() + 1);
            acessoEndPoint.setUltimoAcesso(LocalDateTime.now());
            acessoEndPoint = acessoEndPointRepository.save(acessoEndPoint);
        }

        return null;
    }
}
