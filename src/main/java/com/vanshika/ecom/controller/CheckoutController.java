package com.vanshika.ecom.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.vanshika.ecom.model.ChargeRequest;
import com.vanshika.ecom.model.OrderHistory;
import com.vanshika.ecom.model.Product;
import com.vanshika.ecom.model.User;
import com.vanshika.ecom.repository.OrderHistoryRepository;
import com.vanshika.ecom.repository.ProductRepository;
import com.vanshika.ecom.repository.RegistrationRepository;
import com.vanshika.ecom.service.EmailService;
import com.vanshika.ecom.service.ProductServiceImplem;
import com.vanshika.ecom.service.RegistrationService;
import com.vanshika.ecom.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CheckoutController {

  /*  @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private RegistrationRepository userRepo;

    @Autowired
    private RegistrationService service;

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private ProductServiceImplem prodService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OrderHistoryRepository orderHistoryRepo;


    @GetMapping("/checkout/{username}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String checkout(@PathVariable("username") String username, Model model) {

        User user = service.fetchUserByUsername(username);

        List<String> listOfProdId = stringToList(user.getCart());
        List<String> listOfProdAmt = stringToList(user.getCartProdAmt());
        int len = listOfProdId.size();

        for(int i = 0; i < len; i++){
            String prodId = listOfProdId.get(i);
            Long id = Long.parseLong(prodId);
            String prodAmt = listOfProdAmt.get(i);
            Integer amt = Integer.parseInt(prodAmt);

            Product product = prodService.findProductUsingId(id);

            //sending mail to seller
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(product.getSellerUsername());
            mailMessage.setSubject("Product sold!");
            mailMessage.setFrom("gomailsender@gmail.com");
            if(product.getStock()-amt < 3){

                mailMessage.setText("Hello seller! Your product with following specifications:\n\nProduct Name: " + product.getName() + "\nProduct Category: " + product.getCategory() + "\nProduct Subcategory: "
                        + product.getSubCategory() + "\nProduct Quantity: " + amt + "\nProduct Price: Rs." + product.getPrice() + "\nTotal Amount: Rs." + amt*product.getPrice()
                        +  "\n\nhas been sold.The stock of this product is quite low. Please replenish the stock to continue selling. Thank you for your cooperation. \n\n\n\nRegards: @Team ClickNShip.");

            }
            else{
                mailMessage.setText("Hello seller! Your product with following specifications:\n\nProduct Name: " + product.getName() + "\nProduct Category: " + product.getCategory() + "\nProduct Subcategory: "
                        + product.getSubCategory() + "\nProduct Quantity: " + amt + "\nProduct Price: Rs. " + product.getPrice() + "\nTotal Amount: Rs." + amt*product.getPrice()
                        +  "\n\nhas been sold. Thank you for your cooperation. \n\n\n\nRegards: @Team ClickNShip.");

            }

            emailService.sendEmail(mailMessage);

            product.setStock(product.getStock()-amt);
            prodRepo.save(product);
        }

        //storing order details in order history
        OrderHistory orderHistory= new OrderHistory(user, user.getCart(), user.getCartProdAmt(), user.getBillingAmt());
        orderHistoryRepo.save(orderHistory);


        //clearing cart
        user.setCart("");
        user.setCartProdAmt("");
        userRepo.save(user);

        //sending mail to buyer
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Order Placed successful");
        mailMessage.setFrom("gomailsender@gmail.com");
        mailMessage.setText("Hello " + user.getFirstName() + "!\n\n ThankYou for shopping with us!! Your order has been successfully placed. Your total billing amount: Rs."
                + user.getBillingAmt() + 50.0 + "\n\n P.s. Your order won't be delivered to you due to delivery and payment issues. For further information contact our Customer Service. We hope to see you soon!\n\n\n\nRegards: @Team ClickNShip");

        emailService.sendEmail(mailMessage);

        //reinitializing billing amount
        int amtPayable = (int)user.getBillingAmt();
        user.setBillingAmt(0.0);
        userRepo.save(user);

        model.addAttribute("amount", amtPayable*100);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.INR);

        return "checkout";
    }

    @PostMapping("/charge")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setCurrency(ChargeRequest.Currency.INR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

    //converting string to list
    public List<String> stringToList(String str){
        int l = str.length();
        String s = "";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < l; i++){
            if(str.charAt(i) == ';'){
                list.add(s);
                s = "";
            }
            else{
                s += str.charAt(i);
            }
        }
        return list;
    }*/
}
