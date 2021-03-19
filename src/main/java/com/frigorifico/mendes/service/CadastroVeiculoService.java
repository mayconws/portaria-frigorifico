package com.frigorifico.mendes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.repository.Veiculos;

@Service
public class CadastroVeiculoService {

	@Autowired
	private Veiculos veiculos;

//	@Autowired
//	private FotoStorage fotoStorage;

	@Transactional
	public void salvar(Veiculo veiculo) {
		veiculos.save(veiculo);

	}

	@Transactional
	public void excluir(Long codigo) {
		veiculos.delete(codigo);
	}

//	@Transactional
//	public void excluir(Veiculo veiculo) {
//		try {
//			String foto = veiculo.getFoto();
//			veiculos.delete(veiculo);
//			veiculos.flush();
//			fotoStorage.excluir(foto);
//		} catch (PersistenceException e) {
//			throw new ImpossivelExcluirEntidadeException("Impossível apagar veículo. O mesmo já foi usado na movimentação de veículos.");
//		}
//	}	

}
