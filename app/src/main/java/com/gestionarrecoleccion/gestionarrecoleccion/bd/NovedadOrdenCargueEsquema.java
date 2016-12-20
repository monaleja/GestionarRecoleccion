package com.gestionarrecoleccion.gestionarrecoleccion.bd;

import android.provider.BaseColumns;

/**
 * Created by Alejandra on 19/12/2016.
 */

public class NovedadOrdenCargueEsquema {

    public static abstract class NovedadOrdenCargue implements BaseColumns {

        public static final String tablaNombre = "tb_itemnovedadordencargue";
        public static final String novedadCodigo = "itenovordcar_codigo";
        public static final String ordenCargueCodigo = "ordcar_codigo";
        public static final String novedadObservacion = "itenovordcar_mensaje";
        public static final String tipoNovedadCodigo = "tipnov_codigo";
        public static final String novedadFechaCreacion = "itenovordcar_fechacreacion";
        public static final String novedadHoraCreacion = "itenovordcar_horacreacion";
        public static final String novedadUsuarioCodigo = "usuario_codigo";
        public static final String novedadSincronizado = "sincronizado";
    }
}