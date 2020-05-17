#include <iostream>
#include <vector>
#include <cmath>
#include <fstream>
#include <string>

#define M_PI 3.14159
#define bitsRestriction 10
#define N 150
#define tb 0.1

using namespace std;

void sinus(
	double Amplituda,
	double faza,
	double phi,
	int ilosc_probek,
	vector<double>& results);
void zapis(vector<double>& results, string nazwa_pliku);
void klucz_ASK(int ilosc_probek, string bity, bool restrict)
{
	vector<double> wyniki;
	bool loop = true;
	double a1 = 0;
	double a2 = 2;
	double f = 11;
	double fi = 0;


	for (int i = 0; i < bity.size() && loop; ++i)
	{
		if (bity[i] == '0')
			sinus(a1, f, fi, ilosc_probek, wyniki);
		else
			sinus(a2, f, fi, ilosc_probek, wyniki);

		if (restrict && i >= bitsRestriction)
		{
			loop = false;
		}

	}
	zapis(wyniki, "../klucz_ASK.csv");
}
void klucz_PSK(int ilosc_probek, string bity, bool restrict) {
	vector<double> wyniki;
	bool loop = true;
	double A = 2;
	double f =11;
	double fi1 = 0;
	double fi2 = M_PI;


	for (int i = 0; i < bity.size() && loop; ++i)
	{
		if (bity[i] == '0')
			sinus(A, f, fi1, ilosc_probek, wyniki);
		else
			sinus(A, f, fi2, ilosc_probek, wyniki);


		if (restrict && i >= bitsRestriction)
		{
			loop = false;
		}
	}
	zapis(wyniki, "../klucz_PSK.csv");}
void klucz_FSK(int ilosc_probek, string bity, bool restrict) {

	vector<double> wyniki;
	bool loop = true;
	double A = 2;
	double f1 = (N+1)/tb;
	double f2 = (N+2)/tb;
	double fi = 0; 

	for (int i = 0; i < bity.size(); ++i)
	{
		if (bity[i] == '0')
			sinus(A, f1, fi, ilosc_probek, wyniki);
		else
			sinus(A, f2, fi, ilosc_probek, wyniki);

		if (restrict && i >= bitsRestriction)
		{
			loop = false;
		}
	}

	zapis(wyniki, "../klucz_FSK.csv");

}

namespace sygnaly
{
	string ASK = "0101000001100001011011100110010001100001";
	string PSK = "0101000001100001011011100110010001100001";
	string FSK = "0101000001100001011011100110010001100001";
}

void main()
{
	int ilosc_probek = 10;

	klucz_ASK(ilosc_probek, sygnaly::ASK, false);
	klucz_PSK(ilosc_probek, sygnaly::PSK, false);
	klucz_FSK(ilosc_probek, sygnaly::FSK, false);


}

vector<double> dane_(string nazwa_pliku)
{
	vector<double> wyniki;
	ifstream file(nazwa_pliku, ios::in);
	if (file.good())
	{
		while (!file.eof())
		{
			double tmp;
			file >> tmp;
			wyniki.push_back(tmp);
		}
		return wyniki;
	}
	file.close();
}

void sinus(double Amplituda, double faza, double phi, int ilosc_probek, vector<double>& results)
{
	for (int i = 0; i < ilosc_probek; ++i)
	{
		double tmp = Amplituda * sin(phi + 2 * M_PI * faza * double(i) / (double)ilosc_probek);
		results.push_back(tmp);
	}
}

void zapis(vector<double>& results, string nazwa_pliku)
{
	fstream fp(nazwa_pliku, ios_base::out);
	if (fp.good())
		for (int i = 0; i, i < results.size(); ++i)
		{
			fp << results[i] << endl;
		}
	fp.close();
}