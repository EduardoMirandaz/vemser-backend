package br.com.vemser.pessoaapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dados-pessoais")
@RequiredArgsConstructor
public class DadosPessoaisController {

//    @Autowired
//    private DadosPessoaisService dadosPessoaisService;
//
//    @GetMapping
//    public List<DadosPessoaisDTO> listaDadosPessoais(){
//        return dadosPessoaisService.listaDadosPessoais();
//    }
//
//    @PostMapping
//    public DadosPessoaisDTO create (@RequestBody DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
//        return dadosPessoaisService.create(dadosPessoaisDTO);
//    }
//
//    @PutMapping("/{cpf}")
//    public DadosPessoaisDTO update (@PathVariable("cpf") String cpf, @RequestBody DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
//        return dadosPessoaisService.update(cpf, dadosPessoaisDTO);
//    }
//
//    @GetMapping("/{cpf}")
//    public DadosPessoaisDTO getByCpf(@PathVariable("cpf") String cpf){
//        return dadosPessoaisService.getByCpf(cpf);
//    }
//
//    @DeleteMapping("/{cpf}")
//    public void delete(@PathVariable("cpf") String cpf){
//        dadosPessoaisService.delete(cpf);
//    }
}
