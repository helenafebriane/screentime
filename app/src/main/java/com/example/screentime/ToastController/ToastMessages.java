package com.example.screentime.ToastController;

public class ToastMessages {


    // toast messages displayed when app usage limit is exceeded
    public static final String HOURLY_LAUNCH_LIMIT = " Sudah Mencapai Batas Peluncuran Per Jam !";
    public static final String DAILY_LAUNCH_LIMIT = " Sudah Mencapai Batas Peluncuran Harian !";
    public static final String HOURLY_TIME_LIMIT = " Sudah Mencapai Batas Waktu Per Jam !";
    public static final String DAILY_TIME_LIMIT = " Sudah Mencapai Batas Waktu Harian !";
    public static final String SPECIFIC_TIME_LIMIT = " Tidak Diizinkan Akses Saat Ini !";


    // toast messages displayed when app disabled option is selected
    public static final String APP_WAS_ALREADY_DISABLED_ONCE = "Screentime telah dinonaktifkan sekali hari ini. ";
    public static final String APP_DISABLED_SUCCESS = "Screentime dinonaktifkan selama 10 menit. Nikmati !";
    public static final String AUTOSTART_PERMISSION_NOT_REQUIRED = "Izin AUTOSTART tidak diperlukan pada perangkat ini.";


    // displayed when given time slot limit is not set due to leaving time
    // picker fragment in the middle
    public static final String TIME_LIMIT_NOT_SET = "Batas Waktu Tidak Diatur";


    // displayed when usage restriction is removed from the selected app
    public static final String USAGE_RESTRICTION_REMOVED = "Pembatasan Pemakaian berhasil dihapus dari ";


    // toast messages displayed while setting restriction on a given app
    // and the user is to be prompted while validating fields
    public static final String SELECT_AN_APP = "Silakan pilih aplikasi";
    public static final String SELECT_AT_LEAST_ONE = "Silakan pilih setidaknya satu pilihan";
    public static final String FILL_AT_LEAST_ONE_ENTRY = "Harap isi setidaknya satu entri";
    public static final String LAUNCH_PER_DAY_MORE_THAN_LAUNCH_PER_HOUR = "Peluncuran yang diizinkan per hari harus lebih dari peluncuran yang diizinkan per jam!";
    public static final String HOUR_HAS_60_MIN = "Satu jam hanya memiliki 60 menit!";
    public static final String DAY_HAS_24_HOURS = "Sehari hanya memiliki 24 jam!";
    public static final String TIME_PER_DAY_MORE_THAN_TIME_PER_HOUR = "Waktu yang diizinkan per hari harus lebih dari waktu yang diizinkan per jam";
    public static final String SELECT_AT_LEAST_TIME_SLOT = "Harap atur setidaknya satu Slot Waktu";
    public static final String LIMIT_SET = "Batas telah berhasil ditetapkan ";
}
