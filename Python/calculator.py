import tkinter as tk
from tkinter import messagebox
from functools import partial

def main():
    # create gui window
    calc = tk.Tk()

    # window settings
    calc.title('Calculator app')
    calc.geometry('300x400+400+200')
    font = ('Arial', 30)

    # input field
    input_field = tk.Entry(width=30, font=font)
    input_field.pack()

    # button command functions
    append_text = lambda txt: input_field.insert(tk.END, txt)
    def evaluate():
        try:
            result = eval(input_field.get())
        except Exception:
            messagebox.showerror('Error', 'Invalid expression')
            input_field.delete(0, tk.END)
            return
        input_field.delete(0, tk.END)
        input_field.insert(0, result)

    # number buttons
    button_frame = tk.Frame(calc)
    button_w, button_h = 2, 2
    for i in range(1, 10):
        button = tk.Button(button_frame, text=i,
                           width=button_w, height=button_h, font=font,
                           command=partial(append_text, i))
        button.grid(row=(i-1)//3, column=(i-1)%3)
    for i,eB in enumerate('.0='):
        button = tk.Button(button_frame, text=eB,
                           width=button_w, height=button_h, font=font,
                           command=partial(append_text, eB) if i != 2 else evaluate)
        button.grid(row=3, column=i)
    button_frame.pack(side=tk.LEFT)

    # extra buttons (operators + clear)
    extras_frame = tk.Frame(calc)
    for i,op in enumerate('+-*/'):
        button = tk.Button(extras_frame, text=op,
                           width=button_w, height=1, font=font, pady=10,
                           command=partial(append_text, op))
        button.grid(row=i, column=0)
    clear_button = tk.Button(extras_frame, text='C',
                             width=button_w, height=1, font=font, pady=10,
                             command=lambda: input_field.delete(0, tk.END))
    clear_button.grid(row=4, column=0)
    extras_frame.pack(side=tk.RIGHT)

    calc.mainloop()

if __name__ == '__main__':
    main()