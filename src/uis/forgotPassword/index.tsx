/* eslint-disable @next/next/no-img-element */
import React from "react";
import Input from "../common/input";
import Button from "../common/button";

const ForgotPassword: React.FC = () => {
  return (
    <div className="flex items-center justify-center mt-8 mb-8">
      <div className="bg-white shadow-lg rounded-lg p-8 max-w-md w-full">
        <div className="flex mb-4">
          <img
            src="./forgotPassword.png"
            alt="Reset Password"
            className="w-16 h-16"
          />
        </div>

        <h2 className="text-heading-4 font-bold font-quicksand  text-text-heading  mb-2">
          Forgot your password?
        </h2>
        <p className="text-text-body text-text-medium text-center mb-3">
          No worries! Enter your email and set a new password.
        </p>

        <form className="space-y-4">
          <div>
            <label className="block text-text-heading font-normal mb-1">
              Email or Username
            </label>
            <Input
              variant="normal"
              type="email"
              placeholder="Enter your email"
              className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2"
              required
            />
          </div>

          <div>
            <label className="block  text-text-heading font-normal mb-1">
              New Password
            </label>
            <Input
              variant="normal"
              type="password"
              placeholder="Enter new password"
              className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2"
              required
            />
          </div>

          <div>
            <label className="block  text-text-heading font-normal mb-1">
              Confirm Password
            </label>
            <Input
              variant="normal"
              type="password"
              placeholder="Confirm new password"
              className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 "
              required
            />
          </div>

          {/* Terms Agreement */}
          <div className="flex items-center">
            <input type="checkbox" className="w-4 h-4 mr-2" required />
            <span className="text-text-body  text-sm">
              I agree to the terms & policy .
            </span>
          </div>

          {/* Submit Button */}
          <Button
            variant="secondary"
            type="submit"
            className="w-full text-white font-semibold py-3 rounded-md transition"
          >
            Reset Password
          </Button>
        </form>
      </div>
    </div>
  );
};

export default ForgotPassword;
