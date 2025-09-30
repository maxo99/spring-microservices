import { Router } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { AsyncPipe, JsonPipe } from "@angular/common";
import { Component, inject, OnInit } from '@angular/core';
import { OidcSecurityService } from "angular-auth-oidc-client";
import { ProductService } from "../../services/product/product";
import { OrderService } from "../../services/order/order";
import { Product } from "../../model/product";
import { Order } from "../../model/order";

@Component({
  selector: 'app-homepage',
  templateUrl: './home-page.html',
  standalone: true,
  imports: [
    // AsyncPipe,
    // JsonPipe,
    FormsModule
  ],
  styleUrl: './home-page.css'
})
export class HomePageComponent implements OnInit {
  private readonly oidcSecurityService = inject(OidcSecurityService);
  private readonly productService = inject(ProductService);
  private readonly orderService = inject(OrderService);
  private readonly router = inject(Router);
  isAuthenticated = false;
  products: Array<Product> = [];
  quantityIsNull = false;
  orderSuccess = false;
  orderFailed = false;

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(
      ({ isAuthenticated }) => {
        this.isAuthenticated = isAuthenticated;
        this.productService.getProducts()
          .pipe()
          .subscribe(product => {
            this.products = product;
          })
      }
    )
  }

  goToCreateProductPage() {
    this.router.navigateByUrl('/add-product');
  }

  orderProduct(product: Product, quantity: string) {

    this.oidcSecurityService.userData$.subscribe(result => {
      const userDetails = {
        email: result.userData.email,
        firstName: result.userData.firstName,
        lastName: result.userData.lastName
      };

      if (!quantity) {
        this.orderFailed = true;
        this.orderSuccess = false;
        this.quantityIsNull = true;
      } else if (!product.skuCode) {
        console.error('Product is missing skuCode:', product);
        this.orderFailed = true;
        this.orderSuccess = false;
      } else {
        const order: Order = {
          skuCode: product.skuCode,
          price: product.price,
          quantity: Number(quantity),
          userDetails: userDetails
        }

        console.log('Creating order with:', order);
        this.orderService.orderProduct(order).subscribe(() => {
          this.orderSuccess = true;
          this.orderFailed = false;
        }, error => {
          this.orderFailed = true;
          this.orderSuccess = false;
        })
      }
    })
  }
}
