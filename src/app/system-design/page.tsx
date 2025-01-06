export default function ColorTest() {
    return (
      <div className="p-8 space-y-8">
        {/* Brand Colors */}
        <section>
          <h2>Brand Colors</h2>
          <div className="space-y-2">
            <div className="bg-brand-primary w-16 h-16"> hi lo</div>
            <div className="bg-brand-secondary w-16 h-16">hi lo </div>
          </div>
        </section>
  
        {/* Scale Colors */}
        <section>
          <h2>Scale Colors</h2>
          <div className="space-y-2">
            <div className="bg-scale-color1 w-16 h-16">hi lo </div>
            <div className="bg-scale-color2 w-16 h-16">hi lo</div>
            <div className="bg-scale-color3 w-16 h-16">hi lo</div>
            <div className="bg-scale-color4 w-16 h-16">hi lo </div>
          </div>
        </section>
  
        {/* System Colors */}
        <section>
          <h2>System Colors</h2>
          <div className="space-y-2">
            <div className="bg-system-primary w-16 h-16">hi lo </div>
            <div className="bg-system-success w-16 h-16">hi lo </div>
            <div className="bg-system-danger w-16 h-16">hi lo</div>
            <div className="bg-system-warning w-16 h-16">hi lo</div>
            <div className="bg-system-info w-16 h-16">hi lo</div>
          </div>
        </section>
  
        {/* Text Colors */}
        <section>
          <h2>Text Colors</h2>
          <div className="space-y-2">
            <p className="text-text-heading">Heading Text</p>
            <p className="text-text-body">Body Text</p>
          </div>
        </section>
      </div>
    );
  }
  