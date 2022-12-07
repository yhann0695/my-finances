import { DecimalPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../categoria/service/categoria.service';
import { LancamentoService } from '../lancamento/service/lancamento.service';
import { AuthService } from '../security/auth.service';
import { GeralService } from '../shared/geral.service';
import { UsuarioService } from '../usuario/service/usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public saldoUsuario: number = 0;
  public saldoDespesas: number = 0;
  public saldoReceitas: number = 0;
  public dataHoje: string;
  public mesAtual: string;
  public mesAnterior: string;

  pieChartDataMes: any;
  pieChartDataMesAnterior: any;

  options = {
    tooltips: {
      callbacks: {
        label: (tooltipItem: any, data: any) => {
          const dataset = data.datasets[tooltipItem.datasetIndex];
          const valor = dataset.data[tooltipItem.index];
          const label = dataset.label ? (dataset.label + ': ') : '';
          
          return label + this.decimalPipe.transform(valor, '1.2-2');
        }
      }
    }
  }
  
  constructor(
    private usuarioService: UsuarioService, 
    private categoriaService: CategoriaService,
    private lancamentoService: LancamentoService,
    private geralService: GeralService,
    private decimalPipe: DecimalPipe,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.obterSaldo();
    this.obterSaldoDespesas();
    this.obterSaldoReceitas();
    this.apresentarDataHoje();
    this.apresentarMesAtual();
    this.apresentarMesAnterior();
    this.configDespesaMensalTotalPorCategoria();
    this.configDespesaMesAnteriorTotalPorCategoria();
  }

   public apresentarDataHoje() {
    var data = new Date();
    var day = ["Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"][data.getDay()];
    var date = data.getDate();
    var month = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"][data.getMonth()];
    var year = data.getFullYear();
    this.dataHoje = `${day}, ${date} de ${month} de ${year}`;
    return this.dataHoje;
  }

  public apresentarMesAtual() {
    var data = new Date();
    var month = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"][data.getMonth()];
    this.mesAtual = `${month}`;
    return this.mesAtual;
  }

  public apresentarMesAnterior() {
    var data = new Date();
    var month = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"][data.getMonth() - 1];
    this.mesAnterior = `${month}`;
    return this.mesAnterior;
  }

  public usuarioLogado() {
    var userLogado = JSON.parse(localStorage.getItem('currentUser'));
    return userLogado.id;
  }

  public obterSaldo() {
    this.usuarioService.obterSaldoUsuario(this.usuarioLogado()).subscribe(
      response => {
        this.saldoUsuario = response;
    }, error => {
      console.log(error);
    }); 
  }

  public obterSaldoDespesas() {
    this.usuarioService.obterSaldoDespesas(this.usuarioLogado()).subscribe(
      response => {
        this.saldoDespesas = response;
    }, error => {
      console.log(error);
    }); 
  }

  public obterSaldoReceitas() {
    this.usuarioService.obterSaldoReceitas(this.usuarioLogado()).subscribe(
      response => {
        this.saldoReceitas = response;
    }, error => {
      console.log(error);
    }); 
  }

  public gerarRelatorioLancamentosMensais() {
    this.lancamentoService.gerarRelatorioLancamentosMensais(this.usuarioLogado()).subscribe(
      response => {
        const url = window.URL.createObjectURL(response);
        window.open(url);
      }, error => {
        this.geralService.mensagemInfo('Não foi possível gerar o relatório, ainda não existe lançamentos desse mês.');
    });
  }

  public configDespesaMensalTotalPorCategoria() {
    this.categoriaService.listarDespesaMensalTotalPorCategoria(this.usuarioLogado()).subscribe(
      dados => {
        this.pieChartDataMes = {
          labels: dados.map(dado => dado.nomeCategoria),
          datasets: [
            {
              data: dados.map(dado => dado.totalLancamentos),
              backgroundColor: ["#FF6384","#36A2EB","#FFCE56","#FF6384", "#000000", "#FF0000", "#00FF00", "#FFFF00", "#808080", "#800080"],
              borderColor: ["#FF6384","#36A2EB","#FFCE56","#FF6384", "#000000", "#FF0000", "#00FF00", "#FFFF00", "#808080", "#800080"]
            }
          ]
        };
      }, error => {
             console.log(error);
    });
  }

  public configDespesaMesAnteriorTotalPorCategoria() {
    this.categoriaService.listarDespesaMesAnteriorTotalPorCategoria(this.usuarioLogado()).subscribe(
      dados => {
        this.pieChartDataMesAnterior = {
          labels: dados.map(dado => dado.nomeCategoria),
          datasets: [
            {
              data: dados.map(dado => dado.totalLancamentos),
              backgroundColor: ["#FF6384","#36A2EB","#FFCE56","#FF6384", "#000000", "#FF0000", "#00FF00", "#FFFF00", "#808080", "#800080"],
              borderColor: ["#FF6384","#36A2EB","#FFCE56","#FF6384", "#000000", "#FF0000", "#00FF00", "#FFFF00", "#808080", "#800080"]
            }
          ]
        };
      }, error => {
             console.log(error);
    });
  }

}
