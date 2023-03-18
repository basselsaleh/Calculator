import tkinter as tk

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

    # number buttons
    button_frame = tk.Frame(calc)
    buton_w, button_h = 2, 2
    for i in range(1, 10):
        button = tk.Button(button_frame, text=str(i), width=buton_w, height=button_h, font=font)
        button.grid(row=(i-1)//3, column=(i-1)%3)
    for i,eB in enumerate(['.', '0', '=']):
        button = tk.Button(button_frame, text=eB, width=buton_w, height=button_h, font=font)
        button.grid(row=3, column=i)
    button_frame.pack()

    calc.mainloop()

if __name__ == '__main__':
    main()