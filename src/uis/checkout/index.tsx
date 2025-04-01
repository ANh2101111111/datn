"use client";

import React from "react";
import { paymentMethods } from "./components/data";
import BoxOrders from "./components/BoxOders";
import Input from "../common/input";
import Button from "../common/button";
import { SubmitHandler, useForm } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";
import { createOrder } from "@/api/order";
import { useAuth } from "@/app/context";
import { useRouter } from "next/navigation";
import toast from "react-hot-toast";
import { queryClient } from "@/app/provider";
import { Route } from "@/types/route";

type FiledValues = {
  fullName: string;
  phoneNumber: string;
  email: string;
  address: string;
  note: string;
  paymentMethod: string;
};

const Checkout = () => {
  const { userId } = useAuth();

  const router = useRouter();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FiledValues>({
    defaultValues: { paymentMethod: paymentMethods[0].id },
  });

  const createOrderMutation = useMutation(createOrder, {
    onSuccess: () => {
      toast.success("Order success");
      router.push(Route.DASHBOARD);
      queryClient.invalidateQueries(["CART"]);
    },
    onError: () => {
      toast.error("Order failed");
    },
  });

  const onSubmit: SubmitHandler<FiledValues> = (values) => {
    if (!userId) return;

    createOrderMutation.mutate({
      userId: Number(userId),
      ...values,
    });
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="container mx-auto p-8 grid grid-cols-1 lg:grid-cols-2 gap-8"
    >
      <div>
        <h2 className="text-heading-2 text-text-heading font-quicksand font-bold mb-4">
          Checkout
        </h2>
        <div className="border p-4 shadow-md">
          <h3 className="font-bold text-heading-4 font-quicksand  mb-4">
            Billing Details
          </h3>
          <div className="grid grid-cols-2 gap-4">
            <div className="col-span-2">
              <Input
                type="text"
                placeholder="Full name *"
                className="border p-2 rounded"
                {...register("fullName", {
                  required: "Please enter your full name",
                })}
              />

              {errors?.fullName?.message && (
                <p className="mt-1 text-red-500 text-sm">
                  {errors.fullName.message}
                </p>
              )}
            </div>

            <div>
              <Input
                type="email"
                placeholder="Email *"
                className="border p-2 rounded col-span-2"
                {...register("email", {
                  required: "Please enter your email",
                })}
              />

              {errors?.email?.message && (
                <p className="mt-1 text-red-500 text-sm">
                  {errors.email.message}
                </p>
              )}
            </div>

            <div>
              <Input
                type="text"
                placeholder="Phone number *"
                className="border p-2 rounded"
                {...register("phoneNumber", {
                  required: "Please enter your phone number",
                })}
              />

              {errors?.phoneNumber?.message && (
                <p className="mt-1 text-red-500 text-sm">
                  {errors.phoneNumber.message}
                </p>
              )}
            </div>

            <div className="col-span-2">
              <Input
                type="text"
                placeholder="Address *"
                className="border p-2 rounded"
                {...register("address", {
                  required: "Please enter your address",
                })}
              />

              {errors?.address?.message && (
                <p className="mt-1 text-red-500 text-sm">
                  {errors.address.message}
                </p>
              )}
            </div>

            <textarea
              placeholder="Note"
              className="border p-2 rounded col-span-2"
            ></textarea>
          </div>
        </div>
      </div>
      <div className="grid grid-cols-1 ">
        <BoxOrders />

        <div className="mt-[20px]">
          <h3 className="font-bold text-heading-4 mb-4">Payment</h3>
          {paymentMethods.map((method) => (
            <div key={method.id} className="flex items-center space-x-2 py-1">
              <input
                type="radio"
                id={method.id}
                value={method.id}
                {...register("paymentMethod")}
              />
              <label htmlFor={method.id} className="cursor-pointer">
                {method.name}
              </label>
            </div>
          ))}

          <Button
            variant="primary"
            className="p-2 mt-4 w-full rounded cursor-pointer text-white font-semibold"
            type="submit"
          >
            Place on Order{" "}
          </Button>
        </div>
      </div>
    </form>
  );
};

export default Checkout;
