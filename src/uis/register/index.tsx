import Button from "../common/button";
import Input from "../common/input";

const Register = () => {
  return (
    <div className="grid grid-cols-1 lg:grid-cols-1 max-w-[400px] w-full mx-auto gap-12 my-16">
      <div className="col-span-1">
        <p className="text-heading-2 text-text-heading font-semibold mb-8 font-quicksand">
          Create an Account
        </p>
        <p className="text-text-muted text-xs mb-8">
          Your personal data will be used to support your experience throughout
          this website, to manage access to your account, and for other purposes
          described in our privacy policy
        </p>
        <div className="flex flex-col gap-6">
          <Input type="username" placeholder="Username" />
          <Input type="email" placeholder="Email" />
          <Input type="password" placeholder="password" />
          <Input type="confirmPassword" placeholder="Confirm password" />
          <Button className="h-16 text-text-medium text-brand-thrid">
            Submit & Register
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Register;
