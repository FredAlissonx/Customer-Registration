package br.com.fred.enums;

    public enum EAreaCodeDDD {
        LANDLINE(2, 3, 4, 5, 7),
        SAO_PAULO(11, 12, 13, 14, 15, 16, 17, 18, 19),
        RIO_DE_JANEIRO(21, 22, 24),
        ESPIRITO_SANTO(27, 28),
        MINAS_GERAIS(31, 32, 33, 34, 35, 37, 38),
        PARANA(41, 42, 43, 44, 45, 46),
        SANTA_CATARINA(47, 48, 49),
        RIO_GRANDE_DO_SUL(51, 53, 54, 55),
        DISTRITO_FEDERAL(61),
        GOIAS(62, 64),
        TOCANTINS(63),
        MATO_GROSSO(65, 66, 67),
        ACRE(68),
        RONDONIA(69),
        BAHIA(71, 73, 74, 75, 77),
        SERGIPE(79),
        PERNAMBUCO(81, 87),
        ALAGOAS(82),
        PARAIBA(83),
        RIO_GRANDE_DO_NORTE(84),
        CEARA(85),
        PIAUI(86),
        PARA(91, 93, 94),
        AMAZONAS(92, 97),
        RORAIMA(95),
        AMAPA(96),
        MARANHAO(98, 99);
        private final Integer[] DDD;

        EAreaCodeDDD(final Integer... DDD) {
            this.DDD = DDD;
        }

        public Integer[] getDDD() {
            return DDD;
        }
    }
