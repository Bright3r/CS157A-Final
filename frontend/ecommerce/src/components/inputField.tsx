import styles from "@/styles/InputField.module.css";

interface InputFieldProps {
  id: string;
  label: string;
  value: string;
  onChange: (value: string) => void;
  placeholder: string;
}

export default function InputField({
  id,
  label,
  value,
  onChange,
  placeholder,
}: InputFieldProps) {
  return (
    <div className={styles.inputGroup}>
      <label htmlFor={id} className={styles.label}>
        {label}
      </label>
      <input
        type="text"
        id={id}
        name={id}
        placeholder={placeholder}
        className={styles.input}
        value={value}
        onChange={(e) => onChange(e.target.value)}
        required
      />
    </div>
  );
}