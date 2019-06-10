package com.itba.hci.smarthome.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.view.activity.LoginActivity;
import com.itba.hci.smarthome.view.activity.SmartHomeActivity;
import com.itba.hci.smarthome.view.fragment.LoginFragment;

/**
 * this class is use to navigate between activities and fragments
 */
public class Navigator {

    public Navigator() {

    }

    /**
     * Activities
     * public ..(Activity from, params)
     * Intent intent = new Intent(from.getApplicationContext(), MyClassActivity.class);
     * intent = MyClassActivity.putParams(*);
     * from.startActivity(intent);
     */


//    public void showHomeClienteActivity(LoginFragment loginFragment) {
//        Intent intent = new Intent(loginFragment.getContext(), HomeClienteActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        loginFragment.startActivity(intent);
//    }
//
//    public void showLoginActivity(HomeClienteActivity homeClienteActivity) {
//        Intent intent = new Intent(homeClienteActivity.getApplicationContext(), LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        homeClienteActivity.startActivity(intent);
//    }


    /**
     * Fragments
     * <p>
     * function declaration(params)
     * openFragment(ActivityFrom, fragment, fragment name, addToBackStack?
     */

//    public void showSignUpActivity(LoginFragment from) {
//        Intent intent = new Intent(from.getContext(), SignUpActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showHomeRestaurantActivity(LoginFragment loginFragment) {
//        Intent intent = new Intent(loginFragment.getContext(), HomeRestaurantActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        loginFragment.startActivity(intent);
//    }
//
    public void showLoginFragment(LoginActivity loginActivity) {
        openFragment(loginActivity, new LoginFragment(), "Eat Trip", false);
    }
//
//    public void showSignUpFragment(SignUpActivity signUpActivity) {
//        openFragment(signUpActivity, new SignUpFragment(), "Eat Trip", false);
//    }
//
//    public void showHomeClienteFragment(HomeClienteActivity homeClienteActivity) {
//        openFragment(homeClienteActivity, new HomeClienteFragment(),
//                "Eat Trip - Home Cliente", false);
//    }
//
//
//    public void showLoginActivity(SignUpFragment from) {
//        Intent intent = new Intent(from.getContext(), LoginActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showHomeRestaurantFragment(HomeRestaurantActivity homeRestaurantActivity) {
//        openFragment(homeRestaurantActivity, new HomeRestaurantFragment(),
//                "Eat Trip - Home Restaurante", false);
//    }
//
//    public void showPedidosClienteFragment(PedidosClientesActivity pedidosClientesActivity) {
//        openFragment(pedidosClientesActivity, new PedidosClientesFragment(),
//                "Eat Trip - Pedidos", false);
//    }
//
//    public void showPerfilFragment(PerfilActivity perfilActivity) {
//        openFragment(perfilActivity, new PerfilFragment(), "Eat Trip - Perfil", false);
//    }
//
//
//    public void showCrearPedidoFragment(CrearPedidoActivity crearPedidoActivity) {
//        openFragment(crearPedidoActivity,new CrearPedidoFragment(), "Eat Trip - Crea tu pedido", false);
//    }


    private Fragment openFragment(SmartHomeActivity from, Fragment fragment, String name, boolean addToBackStack) {
        FragmentTransaction transaction = from.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment, name);
        if (addToBackStack)
            transaction.addToBackStack(name);
        from.setTitle(name);
        transaction.commit();
        from.invalidateOptionsMenu();
        return fragment;
    }

//    public void showCrearPedidoActivity(HomeClienteFragment from) {
//        Intent intent = new Intent(from.getContext(), CrearPedidoActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showMisPedidosActiivty(HomeClienteFragment from) {
//        Intent intent = new Intent(from.getContext(), PedidosClientesActivity.class);
//        intent.putExtra("isCliente", true);
//        from.startActivity(intent);
//    }
//
//    public void showMiPerfilActivity(HomeClienteFragment from) {
//        Intent intent = new Intent(from.getContext(), PerfilActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showPedidosClientesActivity(HomeRestaurantFragment from) {
//        Intent intent = new Intent(from.getContext(), PedidosClientesActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showVerUsuariosActivity(HomeRestaurantFragment from) {
//        Intent intent = new Intent(from.getContext(), UsuariosActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showVerUsuariosFragment(UsuariosActivity usuariosActivity) {
//        openFragment(usuariosActivity, new UsuariosFragment(), "Eat Trip - Ver usuarios", false);
//    }
//
//    public void showHomeClienteActivity(CrearPedidoFragment crearPedidoFragment) {
//        Intent intent = new Intent(crearPedidoFragment.getContext(), HomeClienteActivity.class);
//        crearPedidoFragment.startActivity(intent);
//    }
//
//    public void showHomeClienteActivity(PerfilFragment perfilFragment) {
//        Intent intent = new Intent(perfilFragment.getContext(), HomeClienteActivity.class);
//        perfilFragment.startActivity(intent);
//    }
//
//    public void showDetallePedidoClienteFragment(DetallePedidoClienteActivity detallePedidoClienteActivity, long idPedido) {
//        openFragment(detallePedidoClienteActivity, DetallePedidoClienteFragment.newInstance(idPedido), "Eat Trip - Detalle Pedido", false);
//    }
//
//    public void showHomeClienteActivity(DetallePedidoClienteFragment detallePedidoClienteFragment) {
//        Intent intent = new Intent(detallePedidoClienteFragment.getContext(), HomeClienteActivity.class);
//        detallePedidoClienteFragment.startActivity(intent);
//    }
//
//    public void showDetallePedidoClienteActiivty(PedidosClientesFragment pedidosClientesFragment, Long idItemClicked) {
//        Intent intent = new Intent(pedidosClientesFragment.getContext(), DetallePedidoClienteActivity.class);
//        intent.putExtra("idPedido", idItemClicked);
//        pedidosClientesFragment.startActivity(intent);
//    }
//
//    public void showDetallePedidoRestauranteFragment(DetallePedidoRestauranteActivity detallePedidoRestauranteActivity, long idPedido) {
//        openFragment(detallePedidoRestauranteActivity, DetallePedidoRestauranteFragment.newInstance(idPedido), "Eat Trip - Detalle Pedido", false);
//    }
//
//    public void showDetallePedidoRestauranteActivity(PedidosClientesFragment pedidosClientesFragment, Long idItemClicked) {
//        Intent intent = new Intent(pedidosClientesFragment.getContext(), DetallePedidoRestauranteActivity.class);
//        intent.putExtra("idPedido", idItemClicked);
//        pedidosClientesFragment.startActivity(intent);
//    }
//
//    public void showPedidosClientesActivity(DetallePedidoRestauranteFragment detallePedidoRestauranteFragment) {
//        Intent intent = new Intent(detallePedidoRestauranteFragment.getContext(), PedidosClientesActivity.class);
//        detallePedidoRestauranteFragment.startActivity(intent);
//    }
//
//    public void showHomeClienteActivity(CrearPedidoActivity crearPedidoActivity) {
//        Intent intent = new Intent(crearPedidoActivity.getApplicationContext(), HomeClienteActivity.class);
//        crearPedidoActivity.startActivity(intent);
//    }
//
//    public void showPedidosClientesActivity(DetallePedidoClienteActivity from, boolean isCliente) {
//        Intent intent = new Intent(from.getApplicationContext(), PedidosClientesActivity.class);
//        intent.putExtra("isCliente", isCliente);
//        from.startActivity(intent);
//    }
//
//    public void showPedidosClientesActivity(DetallePedidoRestauranteActivity from) {
//        Intent intent = new Intent(from.getApplicationContext(), PedidosClientesActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showHomeClienteActivity(PedidosClientesActivity from) {
//        Intent intent = new Intent(from.getApplicationContext(), HomeClienteActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showHomeClienteActivity(PerfilActivity from) {
//        Intent intent = new Intent(from.getApplicationContext(), HomeClienteActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void showLoginActivity(SignUpActivity from) {
//        Intent intent = new Intent(from.getApplicationContext(), LoginActivity.class);
//        from.startActivity(intent);
//
//    }
//
//    public void showHomeRestaurantActivity(UsuariosActivity from) {
//        Intent intent = new Intent(from.getApplicationContext(), HomeRestaurantActivity.class);
//        from.startActivity(intent);
//
//    }
//
//    public void showHomeRestaurantActivity(PedidosClientesActivity from) {
//        Intent intent = new Intent(from.getApplicationContext(), HomeRestaurantActivity.class);
//        from.startActivity(intent);
//    }
}

