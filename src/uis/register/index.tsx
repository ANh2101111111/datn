/* eslint-disable @typescript-eslint/no-unused-vars */
"use client";
import { useState } from "react";
import { useRouter } from "next/navigation"; // ✅ Import useRouter để điều hướng
import Button from "../common/button";
import Input from "../common/input";

const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const API_URL =
    "https://apt-electric-oriole.ngrok-free.app/api/user/register";
  const handleRegister = async () => {
    setError("");

    if (!username || !email || !password || !confirmPassword) {
      setError("Please fill in all fields!");
      return;
    }
    if (password !== confirmPassword) {
      setError("Passwords do not match!");
      return;
    }
    if (password.length < 6) {
      setError("Password must be at least 6 characters long!");
      return;
    }

    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, email, password, confirmPassword }),
      });

      const data = await response.json();

      if (response.ok) {
        alert("Registration Successful! Redirecting to login...");
        router.push("/login");
      } else {
        setError(data.message || "Registration failed!");
      }
    } catch (error) {
      setError("Network error! Please try again.");
    }
  };

  return (
    <div className="grid grid-cols-1 lg:grid-cols-1 max-w-[400px] w-full mx-auto gap-12 my-16">
      <div className="col-span-1">
        <p className="text-heading-2 text-text-heading font-semibold mb-8 font-quicksand">
          Create an Account
        </p>
        <p className="text-text-muted text-xs mb-8">
          Your personal data will be used to support your experience throughout
          this website, to manage access to your account, and for other purposes
          described in our privacy policy.
        </p>
        <div className="flex flex-col gap-6">
          <Input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <Input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Input
            type="password"
            placeholder="Confirm Password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          {error && <p className="text-red-500">{error}</p>}
          <Button
            className="h-16 text-text-medium text-brand-thrid"
            onClick={handleRegister}
          >
            Submit & Register
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Register;
