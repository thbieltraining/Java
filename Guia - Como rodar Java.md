# Guia: configurar e rodar Java no seu PC

Passo a passo para instalar o JDK, configurar o ambiente, compilar e executar programas Java (usado na disciplina COM230 - Programação Orientada a Objetos).

## Sumário

1. [Conceitos rápidos](#1-conceitos-rápidos)
2. [Instalar o JDK](#2-instalar-o-jdk)
3. [Configurar JAVA_HOME e PATH](#3-configurar-java_home-e-path)
4. [Verificar a instalação](#4-verificar-a-instalação)
5. [Primeiro programa](#5-primeiro-programa)
6. [Como o Java compila e executa](#6-como-o-java-compila-e-executa)
7. [Configurar o VS Code](#7-configurar-o-vs-code)
8. [Erros comuns](#8-erros-comuns)
9. [Comandos úteis](#9-comandos-úteis)

---

## 1. Conceitos rápidos

| Sigla | O que é | Para que serve |
|-------|---------|----------------|
| **JDK** | Java Development Kit | Compilar **e** executar (inclui `javac`) |
| **JRE** | Java Runtime Environment | Só executar programas já compilados |
| **JVM** | Java Virtual Machine | Executa o bytecode (`.class`) |

Para programar, você precisa do **JDK**, não só do JRE.

**Qual versão instalar?** Prefira uma versão **LTS** (suporte de longo prazo), como **Java 21** ou **Java 25**. Versões não LTS (por exemplo, 26 e 27) funcionam, mas podem mudar rápido. Se o professor ou o material exigir uma versão específica, use a indicada.

---

## 2. Instalar o JDK

Distribuições gratuitas e confiáveis: **Eclipse Temurin** (https://adoptium.net) ou **Oracle JDK** (https://www.oracle.com/java/technologies/downloads/).

### Windows

**Opção A: instalador `.msi` (mais fácil).** Baixe o Temurin `.msi` e, durante a instalação, ative as opções:

- *Set JAVA_HOME variable*
- *Add to PATH*

Se fizer isso, pode pular a [seção 3](#3-configurar-java_home-e-path).

**Opção B: arquivo `.zip`.** Extraia em uma pasta **sem espaços nem acentos**, por exemplo:

```
C:\Java\jdk-21
```

Depois configure `JAVA_HOME` e `PATH` manualmente (seção 3).

### Linux (Debian/Ubuntu)

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

### macOS

Com Homebrew:

```bash
brew install --cask temurin@21
```

---

## 3. Configurar JAVA_HOME e PATH

- `JAVA_HOME`: aponta para a pasta do JDK (a pasta que **contém** `bin`, `lib`, etc.).
- `PATH`: lista de pastas onde o sistema procura comandos como `java` e `javac`.

### Windows

1. Aperte a tecla Windows e digite **variáveis de ambiente**.
2. Abra **Editar as variáveis de ambiente do sistema** e clique em **Variáveis de Ambiente**.
3. Em *Variáveis do usuário*, clique em **Novo**:
   - Nome: `JAVA_HOME`
   - Valor: `C:\Java\jdk-21` (a pasta do seu JDK, **não** a pasta `bin`)
4. Selecione a variável **Path**, clique em **Editar**, depois em **Novo**, e adicione:
   ```
   %JAVA_HOME%\bin
   ```
5. Se existir no `Path` uma entrada de um JDK antigo, **remova-a** ou coloque `%JAVA_HOME%\bin` acima dela. O Windows usa a primeira que encontrar.
6. Clique em OK em todas as janelas e **abra um novo terminal**.

### Linux e macOS

Adicione ao final do arquivo `~/.bashrc` (Linux com bash) ou `~/.zshrc` (macOS):

```bash
export JAVA_HOME=/caminho/do/seu/jdk
export PATH="$JAVA_HOME/bin:$PATH"
```

Para descobrir o caminho:

```bash
# Linux
readlink -f $(which javac) | sed 's:/bin/javac::'

# macOS
/usr/libexec/java_home
```

Depois recarregue o arquivo:

```bash
source ~/.bashrc    # ou: source ~/.zshrc
```

---

## 4. Verificar a instalação

Em um terminal **novo**:

```bash
java -version
javac -version
```

Os dois comandos devem mostrar a versão instalada. Para ver qual instalação está sendo usada:

```bash
# Windows
where javac

# Linux/macOS
which javac
```

Se aparecer uma versão diferente da esperada, outro JDK está antes no `PATH`.

---

## 5. Primeiro programa

Crie um arquivo chamado **`Ola.java`**:

```java
public class Ola {
    public static void main(String[] args) {
        System.out.println("Olá, POO!");
    }
}
```

Na pasta do arquivo, compile e execute:

```bash
javac Ola.java
java Ola
```

- `javac Ola.java` gera o arquivo `Ola.class` (bytecode).
- `java Ola` executa a classe. **Não** coloque `.class` nem `.java` aqui.

Atalho (Java 11 ou superior), que compila e executa em um só passo:

```bash
java Ola.java
```

### Regras importantes

- O nome do arquivo deve ser **idêntico** ao da classe `public` (inclusive maiúsculas e minúsculas).
- Só pode haver **uma** classe `public` por arquivo.
- Evite acentos, `ç`, espaços e caracteres especiais em nomes de arquivos e classes.
- Convenção: `PascalCase` para classes (`MinhaClasse`) e `camelCase` para métodos e variáveis (`minhaVariavel`).

---

## 6. Como o Java compila e executa

```
Ola.java  --javac-->  Ola.class (bytecode)  --JVM-->  execução na máquina
```

1. **Código-fonte (`.java`):** o texto que você escreve.
2. **Compilação (`javac`):**
   - análise léxica e sintática (estrutura da linguagem);
   - análise semântica (tipos, variáveis declaradas, exceções tratadas);
   - geração do **bytecode** em arquivos `.class`.
3. **Execução (`java`):** a JVM
   - carrega as classes (*ClassLoader*);
   - verifica o bytecode;
   - executa, usando interpretador e compilador **JIT**, que traduz os trechos mais usados para código nativo durante a execução.

Por isso o mesmo `.class` roda em qualquer sistema que tenha uma JVM.

Para ver o bytecode de uma classe:

```bash
javap -c Ola
```

---

## 7. Configurar o VS Code

1. Instale o [VS Code](https://code.visualstudio.com/).
2. Instale uma extensão Java, como **Extension Pack for Java** (Microsoft/Red Hat) ou **Oracle Java Platform Extension**.
3. Se a extensão não achar o JDK, abra as configurações (`Ctrl+,`) e procure pela opção de caminho do JDK (por exemplo, `jdk.jdkhome` na extensão da Oracle) e informe a pasta do seu JDK.
4. Recarregue a janela: `Ctrl+Shift+P`, depois **Developer: Reload Window**.
5. Abra um arquivo `.java`. Você deve ver realce de sintaxe, autocompletar e o botão **Run** acima do `main`.

Observação: a extensão da Oracle exige um JDK recente. Se o JDK padrão do sistema for muito antigo (por exemplo, Java 8), o servidor da linguagem não inicia. Instale um JDK mais novo e aponte a extensão para ele.

---

## 8. Erros comuns

| Mensagem | Causa | Solução |
|----------|-------|---------|
| `'javac' não é reconhecido como um comando` | JDK fora do `PATH` ou só o JRE instalado | Conferir `JAVA_HOME` e `%JAVA_HOME%\bin` no `Path`; instalar o JDK; abrir um terminal novo |
| `class X is public, should be declared in a file named X.java` | Nome do arquivo diferente do nome da classe pública | Renomear o arquivo ou a classe para ficarem iguais |
| `Error: Could not find or load main class X` | Executando fora da pasta certa, ou usando `java X.class` | Rodar `java X` na pasta onde está o `X.class` |
| `javac -version` e `java -version` mostram versões diferentes | Dois JDKs no `PATH` | Usar `where javac` / `which javac` e ajustar a ordem do `PATH` |
| `error: invalid source release` / `class file version` | Compilado com uma versão e executado com outra | Usar o mesmo JDK para compilar e executar |
| Realce e autocompletar não funcionam no VS Code | Extensão sem JDK compatível | Apontar a extensão para um JDK recente e recarregar a janela |
| Alterei o `PATH` mas nada mudou | Terminal aberto antes da mudança | Fechar e abrir um novo terminal (ou reiniciar o VS Code) |

---

## 9. Comandos úteis

```bash
javac Arquivo.java              # compila um arquivo
javac *.java                    # compila todos os .java da pasta
javac -d out Arquivo.java       # coloca os .class na pasta "out"
java Arquivo                    # executa a classe Arquivo
java -cp out Arquivo            # executa usando a pasta "out" como classpath
javap -c Arquivo                # mostra o bytecode
jar cfe app.jar Arquivo *.class # empacota em um .jar (Arquivo = classe com main)
java -jar app.jar               # executa o .jar
```